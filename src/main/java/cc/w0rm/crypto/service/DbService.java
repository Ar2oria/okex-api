package cc.w0rm.crypto.service;

import cc.w0rm.crypto.db.domain.Crypto;
import cc.w0rm.crypto.db.domain.TaskDetail;
import cc.w0rm.crypto.model.bo.TaskBO;

import java.util.List;

public interface DbService {
    int saveTask(TaskBO taskBO) throws Exception;

    int saveHistoryCandles(String tableName, List<Crypto> candlesList) throws Exception;

    List<TaskDetail> selectTaskDetailListByBizId(String generateBizId);

    void finishTaskDetail(TaskDetail taskDetail) throws Exception;
}
