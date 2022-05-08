package cc.w0rm.crypto.common;

import java.math.BigInteger;

public class ByteUtil {

    public static String bytesToHex(byte[] bytes) {
        return new BigInteger(1, bytes).toString(16);
    }
}
