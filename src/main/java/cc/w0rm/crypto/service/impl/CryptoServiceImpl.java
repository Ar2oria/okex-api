package cc.w0rm.crypto.service.impl;

import cc.w0rm.crypto.biz.BizException;
import cc.w0rm.crypto.client.okex.OkexClient;
import cc.w0rm.crypto.common.JsonUtil;
import cc.w0rm.crypto.db.domain.Candles;
import cc.w0rm.crypto.db.domain.Task;
import cc.w0rm.crypto.db.domain.TaskDetail;
import cc.w0rm.crypto.db.enums.TaskDetailStatusEnum;
import cc.w0rm.crypto.model.TaskManager;
import cc.w0rm.crypto.model.bo.HistoryCandlesBO;
import cc.w0rm.crypto.model.bo.SaveCryptoConfig;
import cc.w0rm.crypto.model.bo.TaskBO;
import cc.w0rm.crypto.model.dto.CandlesDTO;
import cc.w0rm.crypto.model.dto.HistoryCandlesRequestDTO;
import cc.w0rm.crypto.model.enums.Bar;
import cc.w0rm.crypto.service.CryptoService;
import cc.w0rm.crypto.service.DbService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
public class CryptoServiceImpl implements CryptoService {

    private Map<String, TaskManager> taskMap = new ConcurrentHashMap<>();

    private OkexClient okexClient = new OkexClient();
    private DbService dbService = new DbServiceImpl();


    @Override
    public synchronized void saveCryptoData(SaveCryptoConfig config) throws Exception {
        String taskName = config.getInstId() + "-" + config.getBar();
        if (taskMap.containsKey(taskName)) {
            TaskManager<?> task = taskMap.get(taskName);
            task.remove();
        }

        List<HistoryCandlesBO> taskList = saveTask(config);

        TaskManager<HistoryCandlesBO> taskManager = TaskManager.create(taskList, historyCandlesBO -> {
            if (Objects.isNull(historyCandlesBO)) {
                return;
            }
            List<CandlesDTO> candlesList = okexClient.queryHistoryCandles(historyCandlesBO.getRequestDTO());
            List<Candles> candlesEntryList = convert2CandlesList(candlesList);

            String tableName = getTableName(historyCandlesBO.getTaskDetail());

            int count = dbService.saveHistoryCandles(tableName, candlesEntryList);
            dbService.finishTaskDetail(historyCandlesBO.getTaskDetail());

            log.info("taskDetailId:{}, save:{}.", historyCandlesBO.getTaskDetail().getId(), count);
        }, taskName, config.getThreads(), config.getInterval());

        taskMap.put(taskName, taskManager);
    }

    private List<HistoryCandlesBO> saveTask(SaveCryptoConfig config) throws Exception {
        Task task = convert2Task(config);

        List<HistoryCandlesRequestDTO> requestDTOList = calcAndInitTaskList(config);
        List<TaskDetail> taskDetailList = convert2TaskDetailList(task, requestDTOList);

        TaskBO taskBO = new TaskBO();
        taskBO.setTask(task);
        taskBO.setTaskDetailList(taskDetailList);

        int count = dbService.saveTask(taskBO);
        if (count > 0) {
            return buildHistoryCandlesBO(taskDetailList, requestDTOList);
        }

        List<TaskDetail> newTaskDetailList = dbService.selectTaskDetailListByBizId(task.getBizId());
        return buildHistoryCandlesBO(newTaskDetailList, null);
    }

    private List<HistoryCandlesRequestDTO> calcAndInitTaskList(SaveCryptoConfig config) throws BizException {
        long begin = config.getBegin();
        long end = config.getEnd();
        Bar bar = config.getBar();

        long taskSize = config.getLimit() * bar.getMillis();
        long duration = end - begin;
        long cell = duration / taskSize;
        long mod = duration % taskSize;
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
        return result;
    }


    private String getTableName(TaskDetail taskDetail) {
        String instID = taskDetail.getInstId().replace("-", "_");
        instID = instID.toLowerCase(Locale.ROOT);

        return instID + "_" + taskDetail.getBar();
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

    private List<Candles> convert2CandlesList(List<CandlesDTO> candlesList) {
        if (CollectionUtils.isEmpty(candlesList)) {
            return Collections.emptyList();
        }

        return candlesList.stream()
                .map(this::convert2Candles)
                .collect(Collectors.toList());
    }

    private Candles convert2Candles(CandlesDTO candlesDTO) {
        Candles returnVal = new Candles();
        returnVal.setTs(candlesDTO.getTs());
        returnVal.setO(candlesDTO.getOpen());
        returnVal.setC(candlesDTO.getClose());
        returnVal.setH(candlesDTO.getHigh());
        returnVal.setL(candlesDTO.getLow());
        returnVal.setVol(candlesDTO.getVolume());
        returnVal.setVolccy(candlesDTO.getVolCcy());

        return returnVal;
    }


    private Task convert2Task(SaveCryptoConfig taskConfig) {
        Task task = new Task();
        task.setBizId(taskConfig.generateBizId());
        task.setInstId(taskConfig.getInstId());
        task.setBar(taskConfig.getBar().getDesc());
        task.setBegin(taskConfig.getBegin());
        task.setEnd(taskConfig.getEnd());
        task.setTaskLimit(taskConfig.getLimit());
        task.setTaskThreads(taskConfig.getThreads());
        task.setTaskInterval(taskConfig.getInterval());
        task.setCreateAt(new Date());
        return task;
    }

    private List<TaskDetail> convert2TaskDetailList(Task task, List<?> subTaskList) {
        if (CollectionUtils.isEmpty(subTaskList)) {
            return Collections.emptyList();
        }
        return subTaskList.stream()
                .map(sub -> {
                    TaskDetail taskDetail = new TaskDetail();
                    taskDetail.setBizId(task.getBizId());
                    taskDetail.setInstId(task.getInstId());
                    taskDetail.setBar(task.getBar());
                    taskDetail.setParams(JsonUtil.toJson(sub));
                    taskDetail.setStatus(TaskDetailStatusEnum.DEFAULT.getCode());
                    taskDetail.setStartAt(0L);
                    taskDetail.setCreateAt(task.getCreateAt());
                    taskDetail.setUpdateAt(task.getCreateAt());
                    return taskDetail;
                }).collect(Collectors.toList());
    }

    private List<HistoryCandlesBO> buildHistoryCandlesBO(List<TaskDetail> taskDetailList, List<HistoryCandlesRequestDTO> requestDTOList) {
        if (CollectionUtils.isEmpty(taskDetailList)) {
            return Collections.emptyList();
        }

        if (CollectionUtils.isEmpty(requestDTOList)) {
            requestDTOList = convert2HistoryCandlesRequestDTO(taskDetailList);
        }

        List<HistoryCandlesBO> returnVal = new ArrayList<>(taskDetailList.size());
        for (int i = 0; i < taskDetailList.size(); i++) {
            returnVal.add(new HistoryCandlesBO(taskDetailList.get(i), requestDTOList.get(i)));
        }

        return returnVal;
    }

    private List<HistoryCandlesRequestDTO> convert2HistoryCandlesRequestDTO(List<TaskDetail> taskDetailList) {
        if (CollectionUtils.isEmpty(taskDetailList)) {
            return Collections.emptyList();
        }

        return taskDetailList.stream()
                .map(task -> {
                    String params = task.getParams();
                    return JsonUtil.fromJson(params, new TypeReference<HistoryCandlesRequestDTO>() {
                    });
                }).collect(Collectors.toList());
    }

}
