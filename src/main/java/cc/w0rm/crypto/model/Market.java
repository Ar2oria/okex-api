package cc.w0rm.crypto.model;

import cc.w0rm.crypto.model.bo.CandlesBO;

import java.util.List;

public interface Market {
    String getName();

    boolean hasMore();

    CandlesBO getLastCandles();

    void accept(CandlesBO candles);

    List<CandlesBO> getHistoryCandlesBO(long size);
}
