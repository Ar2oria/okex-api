package cc.w0rm.crypto.model.exchange;

import cc.w0rm.crypto.model.bo.CandleBO;
import cc.w0rm.crypto.model.enums.Bar;
import cc.w0rm.crypto.model.exchange.log.TradeLogger;

import java.util.List;

public interface Exchange {
    void registryLogger(TradeLogger logger);

    List<CandleBO> getHistoryCandleList(String instId, Bar c15m, int size);
}
