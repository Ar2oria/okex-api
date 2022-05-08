package cc.w0rm.crypto.db.repo;

import cc.w0rm.crypto.db.domain.TaskDetail;
import cc.w0rm.crypto.db.domain.TaskDetailExample;
import cc.w0rm.crypto.db.enums.TaskDetailStatusEnum;
import cc.w0rm.crypto.db.mappter.TaskDetailMapper;
import cc.w0rm.crypto.db.mappter.TaskDetailMapperExt;
import cc.w0rm.crypto.manager.DbManager;
import org.apache.ibatis.session.SqlSession;

import java.time.Instant;
import java.util.List;

public class TaskDetailRepo extends BaseRepo {

    private TaskDetailMapper taskDetailMapper = DbManager.getMapper(TaskDetailMapper.class, getSqlSession());
    private TaskDetailMapperExt taskDetailMapperExt = DbManager.getMapper(TaskDetailMapperExt.class, getSqlSession());

    public TaskDetailRepo() {
        super();
    }

    public TaskDetailRepo(SqlSession session) {
        super(session);
    }


    public int batchInsertIgnore(List<TaskDetail> list) {
        return taskDetailMapperExt.batchInsertIgnore(list);
    }

    public List<TaskDetail> selectUnfinishedTaskByBizId(String bizId, int id, int limit) {
        return taskDetailMapperExt.selectUnfinishedTaskByBizId(bizId, id, limit);
    }

    public int finishTaskById(Integer id) {
        TaskDetail taskDetail = new TaskDetail();
        taskDetail.setId(id);
        taskDetail.setStatus(TaskDetailStatusEnum.SUCCESS.getCode());
        taskDetail.setStartAt(Instant.now().toEpochMilli());

        TaskDetailExample example = new TaskDetailExample();
        example.createCriteria()
                .andIdEqualTo(id)
                .andStatusEqualTo(TaskDetailStatusEnum.DEFAULT.getCode());

        return taskDetailMapper.updateByExampleSelective(taskDetail, example);
    }
}
