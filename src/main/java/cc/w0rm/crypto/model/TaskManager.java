package cc.w0rm.crypto.model;

import cc.w0rm.crypto.common.JsonUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Data
public class TaskManager<T> {
    private AtomicInteger finishCount;
    private BlockingQueue<T> queue;
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    private TaskManager() {

    }

    public static <T> TaskManager<T> create(List<T> taskList, Consumer<T> consumer, String name, int threads, long interval) throws Exception {
        int taskCount = taskList.size();

        AtomicInteger finishCount = new AtomicInteger();
        ArrayBlockingQueue<T> queue = new ArrayBlockingQueue<>(taskCount, false, taskList);
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(threads,
                new ThreadFactoryBuilder().setNameFormat(name + "-T%d").build());

        TaskManager<T> returnVal = new TaskManager<>();
        returnVal.setFinishCount(finishCount);
        returnVal.setQueue(queue);
        returnVal.setScheduledThreadPoolExecutor(threadPoolExecutor);

        threadPoolExecutor.scheduleAtFixedRate(() -> {
            T t = null;
            try {
                for (; ; ) {
                    if (threadPoolExecutor.isShutdown()) {
                        return;
                    }
                    if (CollectionUtils.isEmpty(queue) && finishCount.get() == taskCount) {
                        synchronized (TaskManager.class) {
                            if (threadPoolExecutor.isShutdown()) {
                                return;
                            }
                            threadPoolExecutor.shutdown();
                        }
                    }
                    t = queue.poll();
                    if (Objects.nonNull(t)) {
                        break;
                    }
                }
                consumer.accept(t);
                finishCount.incrementAndGet();
            } catch (Exception e) {
                if (Objects.nonNull(t)) {
                    queue.offer(t);
                }
                log.error("处理任务失败！returnVal = {}", JsonUtil.toJson(t), e);
            }
        }, 0L, interval, TimeUnit.MILLISECONDS);


        return returnVal;
    }

    public synchronized void remove() {
        if (scheduledThreadPoolExecutor.isShutdown()){
            return;
        }
        scheduledThreadPoolExecutor.shutdown();
    }
}
