package cc.w0rm.crypto.model;

import cc.w0rm.crypto.model.bo.OrderBO;

import java.util.List;

public interface Exchange {
    void registryLogger(TradeLogger logger);

    boolean buy(Account account, OrderBO order);

    boolean sell(Account account, OrderBO order);

    List<Market> getMarketList(List<String> marketName);
}
