package cc.w0rm.crypto.service.impl;

import cc.w0rm.crypto.biz.BizException;
import cc.w0rm.crypto.common.JsonUtil;
import cc.w0rm.crypto.db.domain.Candle;
import cc.w0rm.crypto.db.domain.Task;
import cc.w0rm.crypto.db.domain.TaskDetail;
import cc.w0rm.crypto.db.repo.CandleRepo;
import cc.w0rm.crypto.db.repo.TaskDetailRepo;
import cc.w0rm.crypto.db.repo.TaskRepo;
import cc.w0rm.crypto.manager.DbManager;
import cc.w0rm.crypto.service.DbService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class DbServiceImpl implements DbService {

    private static final int MAX_SIZE = 100;

    private CandleRepo candleRepo = new CandleRepo();
    private TaskDetailRepo taskDetailRepo = new TaskDetailRepo();

    @Override
    public int saveTask(Task task, List<TaskDetail> taskDetailList) throws Exception {
        return DbManager.openTx(sqlSess -> {
            TaskRepo taskRepo = new TaskRepo(sqlSess);
            TaskDetailRepo taskDetailRepo = new TaskDetailRepo(sqlSess);

            int count = taskRepo.insertIgnore(task);
            if (count == 0) {
                return 0;
            }

            int returnVal = 0;

            List<List<TaskDetail>> partition = Lists.partition(taskDetailList, MAX_SIZE);
            for (List<TaskDetail> part : partition) {
                returnVal += taskDetailRepo.batchInsertIgnore(part);
            }

            return returnVal;
        });
    }


    @Override
    public int saveHistoryCandles(String tableName, List<Candle> candleList) throws Exception {
        if (CollectionUtils.isEmpty(candleList)) {
            return 0;
        }

        int returnVal = 0;

        List<List<Candle>> partition = Lists.partition(candleList, MAX_SIZE);
        for (List<Candle> part : partition) {
            returnVal += candleRepo.batchInsertIgnore(tableName, part);
        }
        return returnVal;
    }

    @Override
    public List<TaskDetail> selectTaskDetailListByBizId(String bizId) {
        int minId = 0;

        List<TaskDetail> returnVal = new ArrayList<>();
        for (; ; ) {
            List<TaskDetail> taskDetails = taskDetailRepo.selectUnfinishedTaskByBizId(bizId, minId, MAX_SIZE);
            if (CollectionUtils.isEmpty(taskDetails)) {
                break;
            }
            returnVal.addAll(taskDetails);

            minId = taskDetails.get(taskDetails.size() - 1).getId();
        }
        return returnVal;
    }

    @Override
    public void finishTaskDetail(TaskDetail taskDetail) throws Exception {
        if (Objects.isNull(taskDetail) || Objects.isNull(taskDetail.getId())) {
            throw new BizException("完成任务状态失败，任务id为空! req = " + JsonUtil.toJson(taskDetail));
        }

        taskDetailRepo.finishTaskById(taskDetail.getId());
    }

    @Override
    public synchronized void createCandlesTable(String tableName) throws Exception {
        if (existsTable(tableName)) {
            return;
        }

        candleRepo.createTable(tableName);
    }

    @Override
    public List<Candle> selectCandleList(String tableName, long nowTs, int size) {
        return candleRepo.selectCandleList(tableName, nowTs, size);
    }

    private boolean existsTable(String tableName) {
        try {
            candleRepo.selectAny(tableName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
