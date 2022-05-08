package cc.w0rm.crypto.common;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Md5Util {
    public static String md5(String str) throws Exception {
        // 此 MessageDigest 类为应用程序提供信息摘要算法的功能
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        // 转换为MD5码
        byte[] digest = md5.digest(str.getBytes(StandardCharsets.UTF_8));
        return ByteUtil.bytesToHex(digest);
    }
}
