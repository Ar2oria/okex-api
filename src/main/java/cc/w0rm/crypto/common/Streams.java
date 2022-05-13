package cc.w0rm.crypto.common;

import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.stream.Stream;

public class Streams {

    public static <T> Stream<T> of(Collection<T> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return Stream.empty();
        }
        return collection.stream();
    }

    public static <T> long size(Collection<T> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return 0L;
        }
        return collection.size();
    }
}
