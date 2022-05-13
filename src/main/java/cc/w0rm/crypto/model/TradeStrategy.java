package cc.w0rm.crypto.model;

import cc.w0rm.crypto.model.bo.CandlesBO;

public interface TradeStrategy {
    void accept(CandlesBO candlesBO, Market market, Account account, Exchange exchange);
}
