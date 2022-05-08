package cc.w0rm.crypto.db.mappter;

import cc.w0rm.crypto.db.domain.Task;
import cc.w0rm.crypto.db.domain.TaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskMapper {
    long countByExample(TaskExample example);

    int deleteByExample(TaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Task row);

    int insertSelective(Task row);

    List<Task> selectByExample(TaskExample example);

    Task selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Task row, @Param("example") TaskExample example);

    int updateByExample(@Param("row") Task row, @Param("example") TaskExample example);

    int updateByPrimaryKeySelective(Task row);

    int updateByPrimaryKey(Task row);
}