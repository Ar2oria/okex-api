package cc.w0rm.crypto.model;

import cc.w0rm.crypto.model.bo.CandlesBO;
import cc.w0rm.crypto.model.bo.VmReportBO;

public interface TradeLogger {
    void log(CandlesBO candlesBO, Account account);

    VmReportBO createReport();
}
