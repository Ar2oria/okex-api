package cc.w0rm.crypto.starter;

import cc.w0rm.crypto.common.DateTimeUtil;
import cc.w0rm.crypto.model.bo.SaveCryptoConfig;
import cc.w0rm.crypto.model.enums.Bar;
import cc.w0rm.crypto.service.CryptoService;
import cc.w0rm.crypto.service.impl.CryptoServiceImpl;

public class Starter {


    public static void main(String[] args) throws Exception {
        SaveCryptoConfig saveCryptoConfig = new SaveCryptoConfig();
        saveCryptoConfig.setInstId("ETH-USDT");
        saveCryptoConfig.setBar(Bar.C_1M);
        saveCryptoConfig.setBegin(DateTimeUtil.parseDateTs("2020-01-01"));
        saveCryptoConfig.setEnd(DateTimeUtil.parseDateTs("2022-05-08"));
        saveCryptoConfig.setLimit(100);
        saveCryptoConfig.setThreads(10);
        saveCryptoConfig.setInterval(1000 / 20);

        CryptoService cryptoService = new CryptoServiceImpl();
        cryptoService.saveCryptoData(saveCryptoConfig);

    }

}
