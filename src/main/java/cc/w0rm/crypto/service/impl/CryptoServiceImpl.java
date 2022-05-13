package cc.w0rm.crypto.service.impl;

import cc.w0rm.crypto.biz.BizException;
import cc.w0rm.crypto.common.JsonUtil;
import cc.w0rm.crypto.common.Streams;
import cc.w0rm.crypto.db.domain.Candles;
import cc.w0rm.crypto.db.domain.Task;
import cc.w0rm.crypto.db.domain.TaskDetail;
import cc.w0rm.crypto.db.enums.TaskDetailStatusEnum;
import cc.w0rm.crypto.manager.ScheduledTaskManager;
import cc.w0rm.crypto.model.bo.*;
import cc.w0rm.crypto.model.enums.Bar;
import cc.w0rm.crypto.service.ClientService;
import cc.w0rm.crypto.service.CryptoService;
import cc.w0rm.crypto.service.DbService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
public class CryptoServiceImpl implements CryptoService {

    private Map<String, ScheduledTaskManager> taskMap = new ConcurrentHashMap<>();

    private ClientService clientService = new ClientServiceImpl();
    private DbService dbService = new DbServiceImpl();


    @Override
    public synchronized void saveCryptoData(SaveCryptoConfig config) throws Exception {
        String taskName = config.getInstId() + "-" + config.getBar();
        if (taskMap.containsKey(taskName)) {
            ScheduledTaskManager<?> task = taskMap.get(taskName);
            task.remove();
        }

        TableInfoBO tableInfoBO = createTable(config);

        List<HistoryCandlesBO> taskList = saveTask(config);
        if (CollectionUtils.isEmpty(taskList)) {
            log.warn("任务列表为空。");
            return;
        }

        ScheduledTaskManager<HistoryCandlesBO> taskManager = ScheduledTaskManager.create(taskList, historyCandlesBO -> {
            if (Objects.isNull(historyCandlesBO)) {
                return;
            }
            List<CandlesBO> candlesList = clientService.queryHistoryCandles(historyCandlesBO.getRequest());
            List<Candles> candlesEntryList = convert2CandlesList(candlesList);

            int count = dbService.saveHistoryCandles(tableInfoBO.getTableName(), candlesEntryList);
            dbService.finishTaskDetail(historyCandlesBO.getTaskDetail());

            log.info("taskDetailId:{}, save:{}.", historyCandlesBO.getTaskDetail().getId(), count);
        }, taskName, config.getThreads(), config.getInterval());

        taskMap.put(taskName, taskManager);
    }

    private TableInfoBO createTable(SaveCryptoConfig config) throws Exception {
        String tableName = getTableName(config);
        dbService.createCandlesTable(tableName);

        TableInfoBO returnVal = new TableInfoBO();
        returnVal.setTableName(tableName);
        return returnVal;
    }

    private List<HistoryCandlesBO> saveTask(SaveCryptoConfig config) throws Exception {
        Task task = convert2Task(config);

        List<CandlesRequestBO> requestList = calcAndInitTaskList(config);
        List<TaskDetail> taskDetailList = convert2TaskDetailList(task, requestList);

        TaskBO taskBO = new TaskBO();
        taskBO.setTask(task);
        taskBO.setTaskDetailList(taskDetailList);

        int count = dbService.saveTask(taskBO);
        if (count > 0) {
            return buildHistoryCandlesBO(taskDetailList, requestList);
        }

        List<TaskDetail> newTaskDetailList = dbService.selectTaskDetailListByBizId(task.getBizId());
        return buildHistoryCandlesBO(newTaskDetailList, null);
    }

    private List<CandlesRequestBO> calcAndInitTaskList(SaveCryptoConfig config) throws BizException {
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
        List<CandlesRequestBO> result = new ArrayList<>((int) cell);
        for (long i = begin; i < end; ) {
            last = Math.min(i + taskSize, end);
            CandlesRequestBO t = newForHistoryCandlesTask(i, last, config);
            result.add(t);
            i = last + 1;
        }
        return result;
    }


    private String getTableName(SaveCryptoConfig config) {
        String instID = config.getInstId().replace("-", "_");
        instID = instID.toLowerCase(Locale.ROOT);

        return instID + "_" + config.getBar().getDesc();
    }

    private static CandlesRequestBO newForHistoryCandlesTask(long start, long end, SaveCryptoConfig config) {
        return CandlesRequestBO.buildRequest(config, start, end);
    }

    private List<Candles> convert2CandlesList(List<CandlesBO> candlesList) {
        if (CollectionUtils.isEmpty(candlesList)) {
            return Collections.emptyList();
        }

        return candlesList.stream()
                .map(this::convert2Candles)
                .collect(Collectors.toList());
    }

    private Candles convert2Candles(CandlesBO candlesBO) {
        Candles returnVal = new Candles();
        returnVal.setTs(candlesBO.getTs());
        returnVal.setO(candlesBO.getOpen());
        returnVal.setC(candlesBO.getClose());
        returnVal.setH(candlesBO.getHigh());
        returnVal.setL(candlesBO.getLow());
        returnVal.setVol(candlesBO.getVolume());
        returnVal.setVolCcy(candlesBO.getVolCcy());

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
                    taskDetail.setParams(JsonUtil.toJson(sub));
                    taskDetail.setStatus(TaskDetailStatusEnum.DEFAULT.getCode());
                    taskDetail.setStartAt(0L);
                    taskDetail.setCreateAt(task.getCreateAt());
                    taskDetail.setUpdateAt(task.getCreateAt());
                    return taskDetail;
                }).collect(Collectors.toList());
    }

    private List<HistoryCandlesBO> buildHistoryCandlesBO(List<TaskDetail> taskDetailList, List<CandlesRequestBO> requestList) {
        if (CollectionUtils.isEmpty(taskDetailList)) {
            return Collections.emptyList();
        }

        if (CollectionUtils.isEmpty(requestList)) {
            requestList = convert2CandlesRequestList(taskDetailList);
        }

        List<HistoryCandlesBO> returnVal = new ArrayList<>(taskDetailList.size());
        for (int i = 0; i < taskDetailList.size(); i++) {
            returnVal.add(new HistoryCandlesBO(taskDetailList.get(i), requestList.get(i)));
        }

        return returnVal;
    }

    private List<CandlesRequestBO> convert2CandlesRequestList(List<TaskDetail> taskDetailList) {
        if (CollectionUtils.isEmpty(taskDetailList)) {
            return Collections.emptyList();
        }

        return Streams.of(taskDetailList)
                .map(CandlesRequestBO::parse)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
