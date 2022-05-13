package cc.w0rm.crypto.model.bo;

import cc.w0rm.crypto.model.Account;
import cc.w0rm.crypto.model.TradeLogger;

public class TimeBasedLogger implements TradeLogger {
    @Override
    public void log(CandlesBO candlesBO, Account account) {

    }

    @Override
    public VmReportBO createReport() {
        return null;
    }
}
