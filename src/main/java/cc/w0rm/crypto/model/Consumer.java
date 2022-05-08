package cc.w0rm.crypto.model;

public interface Consumer<T> {
    void accept(T t) throws Exception;
}
