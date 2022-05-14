package cc.w0rm.crypto.common;

import java.util.Objects;

public class AnnoUtil {

    public static String getResourceName(Class<?> clazz) {
        if (Objects.isNull(clazz)) {
            return null;
        }

        if (!clazz.isAnnotationPresent(Name.class)) {
            return null;
        }

        Name annotation = clazz.getAnnotation(Name.class);
        return annotation.value();
    }

    public static String getResourceName(Object obj) {
        if (Objects.isNull(obj)) {
            return null;
        }

        Class<?> clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(Name.class)) {
            return null;
        }

        Name annotation = clazz.getAnnotation(Name.class);
        return annotation.value();
    }

}
