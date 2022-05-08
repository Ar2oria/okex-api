package cc.w0rm.crypto.db.mappter;

import cc.w0rm.crypto.db.domain.TaskDetail;
import cc.w0rm.crypto.db.domain.TaskDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskDetailMapper {
    long countByExample(TaskDetailExample example);

    int deleteByExample(TaskDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskDetail row);

    int insertSelective(TaskDetail row);

    List<TaskDetail> selectByExample(TaskDetailExample example);

    TaskDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") TaskDetail row, @Param("example") TaskDetailExample example);

    int updateByExample(@Param("row") TaskDetail row, @Param("example") TaskDetailExample example);

    int updateByPrimaryKeySelective(TaskDetail row);

    int updateByPrimaryKey(TaskDetail row);
}