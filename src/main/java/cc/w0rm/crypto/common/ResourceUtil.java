package cc.w0rm.crypto.common;

import cc.w0rm.crypto.config.GlobalConfig;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class ResourceUtil {

    public static String readResource(String fileName) throws Exception {
        InputStream resourceAsStream = GlobalConfig.class.getClassLoader().getResourceAsStream(fileName);
        if (Objects.isNull(resourceAsStream)) {
            throw new IllegalArgumentException("can not found file = " + fileName);
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
        StringBuilder sb = new StringBuilder();
        String buffer;
        while ((buffer = bufferedReader.readLine()) != null) {
            sb.append(buffer);
        }
        return sb.toString();
    }

}
