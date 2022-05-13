package cc.w0rm.crypto.model.bo;

import cc.w0rm.crypto.common.Md5Util;
import cc.w0rm.crypto.model.enums.Bar;
import lombok.Data;

@Data
public class SaveCryptoConfig {
    private String instId;
    private Bar bar;
    private long begin;
    private long end;
    private int limit = 100;
    private int threads = 5;
    private long interval = 1000;

    private String calcHash() {
        try {
            String val = instId + bar + begin + end + limit + threads + interval;
            return Md5Util.md5(val);
        } catch (Exception e) {
            return null;
        }
    }

    public String generateBizId() {
        return calcHash();
    }
}
