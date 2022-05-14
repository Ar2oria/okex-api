package cc.w0rm.crypto.common;

import java.math.BigDecimal;

@FunctionalInterface
public interface BigDecimalToDouble<T> {
    default double convert(T t) {
        return apply(t).doubleValue();
    }

    BigDecimal apply(T t);
}