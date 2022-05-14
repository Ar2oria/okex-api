package cc.w0rm.crypto.model.exchange;

import cc.w0rm.crypto.model.bo.CandleBO;
import cc.w0rm.crypto.model.enums.Bar;
import cc.w0rm.crypto.model.exchange.local.LocalAccountManager;
import cc.w0rm.crypto.model.exchange.local.LocalMarketManager;
import cc.w0rm.crypto.model.exchange.local.LocalOrderManager;
import cc.w0rm.crypto.model.exchange.log.TradeLogger;

import java.util.List;

public class LocalExchange implements Exchange {
    private final MarketManager marketManager;
    private final OrderManager orderManager;
    private final AccountManager accountManager;
    private final LoggerRoot loggerRoot;

    public LocalExchange(DataSource dataSource) {
        marketManager = new LocalMarketManager(dataSource);
        orderManager = new LocalOrderManager();
        accountManager = new LocalAccountManager();
        loggerRoot = new LoggerRoot();
    }

    @Override
    public void registryLogger(TradeLogger logger) {
        loggerRoot.addLogger(logger);
    }

    @Override
    public List<CandleBO> getHistoryCandleList(String instId, Bar c15m, int size) {
        return null;
    }

}
