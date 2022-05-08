package cc.w0rm.crypto.db.repo;

import cc.w0rm.crypto.db.domain.Task;
import cc.w0rm.crypto.db.domain.TaskExample;
import cc.w0rm.crypto.db.mapper.TaskMapper;
import cc.w0rm.crypto.db.mapper.TaskMapperExt;
import cc.w0rm.crypto.manager.DbManager;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TaskRepo extends BaseRepo {
    private TaskMapper taskMapper = DbManager.getMapper(TaskMapper.class, getSqlSession());
    private TaskMapperExt taskMapperExt = DbManager.getMapper(TaskMapperExt.class, getSqlSession());

    public TaskRepo() {
        super();
    }

    public TaskRepo(SqlSession session) {
        super(session);
    }


    public int insertIgnore(Task task) {
        return taskMapperExt.insertIgnore(task);
    }

    public Task selectByBizId(String bizId) {
        TaskExample example = new TaskExample();
        example.createCriteria()
                .andBizIdEqualTo(bizId);

        List<Task> tasks = taskMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(tasks)) {
            return null;
        }

        return tasks.get(0);
    }

}
