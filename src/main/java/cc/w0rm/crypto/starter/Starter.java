package cc.w0rm.crypto.starter;

import cc.w0rm.crypto.common.DateTimeUtil;
import cc.w0rm.crypto.model.bo.SaveCryptoConfig;
import cc.w0rm.crypto.model.enums.Bar;
import cc.w0rm.crypto.service.CryptoService;
import cc.w0rm.crypto.service.impl.CryptoServiceImpl;

public class Starter {


    public static void main(String[] args) throws Exception {
        long interval = 1000 / 20 / 2;
        SaveCryptoConfig btcConfig = createConfig("BTC-USDT", Bar.C_1M, interval);
        SaveCryptoConfig ethConfig = createConfig("ETH-USDT", Bar.C_1M, interval);

        CryptoService cryptoService = new CryptoServiceImpl();
        cryptoService.saveCryptoData(btcConfig);
        cryptoService.saveCryptoData(ethConfig);
    }

    private static SaveCryptoConfig createConfig(String instId, Bar bar, long interval) throws Exception {
        SaveCryptoConfig saveCryptoConfig = new SaveCryptoConfig();
        saveCryptoConfig.setInstId(instId);
        saveCryptoConfig.setBar(bar);
        saveCryptoConfig.setBegin(DateTimeUtil.parseDateTs("2020-01-01"));
        saveCryptoConfig.setEnd(DateTimeUtil.parseDateTs("2022-05-08"));
        saveCryptoConfig.setLimit(100);
        saveCryptoConfig.setThreads(10);
        saveCryptoConfig.setInterval(interval);
        return saveCryptoConfig;
    }

}
