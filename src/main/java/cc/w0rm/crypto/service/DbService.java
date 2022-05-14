package cc.w0rm.crypto.service;

import cc.w0rm.crypto.db.domain.Candle;
import cc.w0rm.crypto.db.domain.Task;
import cc.w0rm.crypto.db.domain.TaskDetail;

import java.util.List;

/**
 * 接口模型为java原生+数据库模型
 */
public interface DbService {
    int saveTask(Task task, List<TaskDetail> taskDetailList) throws Exception;

    int saveHistoryCandles(String tableName, List<Candle> candleList) throws Exception;

    List<TaskDetail> selectTaskDetailListByBizId(String generateBizId);

    void finishTaskDetail(TaskDetail taskDetail) throws Exception;

    void createCandlesTable(String tableName) throws Exception;

    List<Candle> selectCandleList(String tableName, long nowTs, int size);
}
