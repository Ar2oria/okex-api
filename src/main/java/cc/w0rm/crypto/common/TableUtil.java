package cc.w0rm.crypto.common;

import cc.w0rm.crypto.model.enums.Bar;

import java.util.Locale;

public class TableUtil {

    public static String getTableName(String instId, Bar bar) {
        String instID = instId.replace("-", "_");
        instID = instID.toLowerCase(Locale.ROOT);

        return instID + "_" + bar.getDesc();
    }
}
