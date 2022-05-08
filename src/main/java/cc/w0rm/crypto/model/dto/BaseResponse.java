package cc.w0rm.crypto.model.dto;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private int code;
    private String msg;
    private T data;
}
