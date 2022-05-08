package cc.w0rm.crypto.model.bo;

import cc.w0rm.crypto.biz.BizException;
import cc.w0rm.crypto.common.JsonUtil;
import cc.w0rm.crypto.model.Consumer;
import cc.w0rm.crypto.model.dto.HistoryCandlesRequestDTO;
import cc.w0rm.crypto.model.enums.Bar;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Setter
public class Task {
    private AtomicInteger finishCount;
    private BlockingQueue<HistoryCandlesRequestDTO> queue;
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    private Task() {

    }

    public static Task createTask(SaveCryptoConfig config, Consumer<HistoryCandlesRequestDTO> consumer) throws Exception {
        long begin = config.getBegin();
        long end = config.getEnd();
        Bar bar = config.getBar();

        long taskSize = config.getLimit() * bar.getMillis();
        long duartion = end - begin;
        long cell = duartion / taskSize;
        long mod = duartion % taskSize;
        if (mod > 0) {
            cell += 1;
        }
        if (cell > Integer.MAX_VALUE) {
            throw new BizException("任务数量过多，初始化任务失败！");
        }
        long last;
        List<HistoryCandlesRequestDTO> result = new ArrayList<>((int) cell);
        for (long i = begin; i < end; ) {
            last = Math.min(i + taskSize, end);
            HistoryCandlesRequestDTO t = newForHistoryCandlesTask(i, last, config);
            result.add(t);
            i = last + 1;
        }

        int taskCount = result.size();

        Task task = new Task();
        AtomicInteger atomicInteger = new AtomicInteger();
        ArrayBlockingQueue<HistoryCandlesRequestDTO> queue = new ArrayBlockingQueue<>(taskCount, false, result);
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(config.getThreads(),
                new ThreadFactoryBuilder().setNameFormat(config.getInstId() + "-" + config.getBar() + "-T%d").build());
        task.setFinishCount(atomicInteger);
        task.setQueue(queue);
        task.setScheduledThreadPoolExecutor(threadPoolExecutor);

        threadPoolExecutor.scheduleAtFixedRate(() -> {
            HistoryCandlesRequestDTO requestDTO = null;
            try {
                for (; ; ) {
                    if (CollectionUtils.isEmpty(queue) && atomicInteger.get() == taskCount) {
                        if (threadPoolExecutor.isShutdown()) {
                            return;
                        }
                        synchronized (Task.class) {
                            if (threadPoolExecutor.isShutdown()) {
                                return;
                            }
                            threadPoolExecutor.shutdown();
                        }
                    }
                    requestDTO = queue.poll();
                    if (Objects.nonNull(requestDTO)) {
                        break;
                    }
                }
                consumer.accept(requestDTO);
                atomicInteger.incrementAndGet();
            } catch (Exception e) {
                if (Objects.nonNull(requestDTO)) {
                    queue.offer(requestDTO);
                }
                log.error("处理任务失败！task = {}", JsonUtil.toJson(requestDTO), e);
            }
        }, 0L, config.getInterval(), TimeUnit.MILLISECONDS);


        return task;
    }

    private static HistoryCandlesRequestDTO newForHistoryCandlesTask(long start, long end, SaveCryptoConfig config) {
        return HistoryCandlesRequestDTO.builder()
                .instId(config.getInstId())
                .bar(config.getBar())
                .limit(config.getLimit())
                .before(start)
                .after(end)
                .build();
    }

    public void remove() {
        finishCount.set(0);
        queue.clear();
        scheduledThreadPoolExecutor.shutdown();
    }
}
