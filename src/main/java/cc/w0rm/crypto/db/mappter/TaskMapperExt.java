package cc.w0rm.crypto.db.mappter;

import cc.w0rm.crypto.db.domain.Task;

public interface TaskMapperExt {

    int insertIgnore(Task task);

}
