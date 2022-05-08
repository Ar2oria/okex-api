package cc.w0rm.crypto.model.bo;

import cc.w0rm.crypto.model.enums.Bar;
import lombok.Data;

@Data
public class SaveCryptoConfig {
    private String instId;
    private Bar bar;
    private long begin;
    private long end;
    private int limit = 1000;
    private int threads = 5;
    private long interval = 1000;
}
