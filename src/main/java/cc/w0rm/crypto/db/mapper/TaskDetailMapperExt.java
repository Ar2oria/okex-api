package cc.w0rm.crypto.db.mapper;

import cc.w0rm.crypto.db.domain.TaskDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskDetailMapperExt {

    int batchInsertIgnore(@Param("list") List<TaskDetail> list);

    List<TaskDetail> selectUnfinishedTaskByBizId(@Param("bizId") String bizId, @Param("minId") int minId, @Param("limit") int limit);
}
