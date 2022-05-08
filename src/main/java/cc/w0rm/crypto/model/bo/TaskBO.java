package cc.w0rm.crypto.model.bo;

import cc.w0rm.crypto.db.domain.Task;
import cc.w0rm.crypto.db.domain.TaskDetail;
import lombok.Data;

import java.util.List;

@Data
public class TaskBO {
    private Task task;
    private List<TaskDetail> taskDetailList;
}
