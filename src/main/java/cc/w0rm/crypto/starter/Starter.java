package cc.w0rm.crypto.starter;

import cc.w0rm.crypto.common.DateTimeUtil;
import cc.w0rm.crypto.model.bo.SaveCryptoConfig;
import cc.w0rm.crypto.model.enums.Bar;
import cc.w0rm.crypto.service.impl.CryptoServiceImpl;

public class Starter {


    public static void main(String[] params) throws Exception {
        long interval = 1000 / 10;
        SaveCryptoConfig lunaConfig = createConfig("OKB-USDT", Bar.C_1M, "2020-01-01", "2022-05-08", interval);

        CryptoServiceImpl cryptoService = new CryptoServiceImpl();
        cryptoService.saveCryptoData(lunaConfig);
    }

    private static SaveCryptoConfig createConfig(String instId, Bar bar, String start, String end, long interval) throws Exception {
        SaveCryptoConfig saveCryptoConfig = new SaveCryptoConfig();
        saveCryptoConfig.setInstId(instId);
        saveCryptoConfig.setBar(bar);
        saveCryptoConfig.setBegin(DateTimeUtil.parseDateTs(start));
        saveCryptoConfig.setEnd(DateTimeUtil.parseDateTs(end));
        saveCryptoConfig.setLimit(100);
        saveCryptoConfig.setThreads(10);
        saveCryptoConfig.setInterval(interval);
        return saveCryptoConfig;
    }

}
