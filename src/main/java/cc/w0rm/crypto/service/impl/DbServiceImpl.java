package cc.w0rm.crypto.service.impl;

import cc.w0rm.crypto.biz.BizException;
import cc.w0rm.crypto.common.JsonUtil;
import cc.w0rm.crypto.db.domain.Candles;
import cc.w0rm.crypto.db.domain.TaskDetail;
import cc.w0rm.crypto.db.repo.CandlesRepo;
import cc.w0rm.crypto.db.repo.TaskDetailRepo;
import cc.w0rm.crypto.db.repo.TaskRepo;
import cc.w0rm.crypto.manager.DbManager;
import cc.w0rm.crypto.model.bo.TaskBO;
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

    @Override
    public int saveTask(TaskBO taskBO) throws Exception {
        return DbManager.openTx(sqlSess -> {
            TaskRepo taskRepo = new TaskRepo(sqlSess);
            TaskDetailRepo taskDetailRepo = new TaskDetailRepo(sqlSess);

            int count = taskRepo.insertIgnore(taskBO.getTask());
            if (count == 0) {
                return 0;
            }

            int returnVal = 0;

            List<List<TaskDetail>> partition = Lists.partition(taskBO.getTaskDetailList(), MAX_SIZE);
            for (List<TaskDetail> part : partition) {
                returnVal += taskDetailRepo.batchInsertIgnore(part);
            }

            return returnVal;
        });
    }


    @Override
    public int saveHistoryCandles(String tableName, List<Candles> candlesList) throws Exception {
        if (CollectionUtils.isEmpty(candlesList)) {
            return 0;
        }

        CandlesRepo repo = new CandlesRepo();

        int returnVal = 0;

        List<List<Candles>> partition = Lists.partition(candlesList, MAX_SIZE);
        for (List<Candles> part : partition) {
            returnVal += repo.batchInsertIgnore(tableName, part);
        }
        return returnVal;
    }

    @Override
    public List<TaskDetail> selectTaskDetailListByBizId(String bizId) {
        TaskDetailRepo taskDetailRepo = new TaskDetailRepo();

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

        TaskDetailRepo taskDetailRepo = new TaskDetailRepo();
        taskDetailRepo.finishTaskById(taskDetail.getId());
    }
}
