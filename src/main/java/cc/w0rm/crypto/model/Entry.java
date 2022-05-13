package cc.w0rm.crypto.model;

import lombok.Data;

@Data
public class Entry<T> {

    private T data;

    private Entry<T> last;
    private Entry<T> next;

}
