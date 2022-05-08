package cc.w0rm.crypto.db.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskDetailStatusEnum {

    DEFAULT(0, "初始状态"),
    SUCCESS(1, "处理完成")

    ;
    public int code;
    public String desc;

}
