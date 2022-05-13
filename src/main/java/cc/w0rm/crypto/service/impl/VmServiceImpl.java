package cc.w0rm.crypto.service.impl;

import cc.w0rm.crypto.common.Streams;
import cc.w0rm.crypto.model.*;
import cc.w0rm.crypto.model.bo.*;
import cc.w0rm.crypto.service.VmService;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class VmServiceImpl implements VmService {

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,
            100, 5, TimeUnit.MINUTES, new SynchronousQueue<>(),
            new ThreadFactoryBuilder().setNameFormat("VmServiceImpl - T%d").build());

    @Override
    public VmReportBO startVm(VmTaskBO vmTaskConfig) throws Exception {
        Exchange exchange = vmTaskConfig.getExchange();
        Account account = vmTaskConfig.getAccount();
        TradeStrategy tradeStrategy = vmTaskConfig.getTradeStrategy();

        TradeLogger logger = createTradeLogger(exchange);

        List<StockBO> stockList = account.getHoldStockList();
        Set<String> marketName = Streams.of(stockList)
                .map(StockBO::getMarketName)
                .collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(marketName)) {
            return logger.createReport();
        }

        List<Market> marketList = exchange.getMarketList(new ArrayList<>(marketName));
        List<CompletableFuture<Void>> taskList = Streams.of(marketList)
                .map(market -> CompletableFuture.runAsync(() -> {
                    for (; ; ) {
                        if (!market.hasMore()) {
                            break;
                        }
                        CandlesBO lastCandles = market.getLastCandles();
                        tradeStrategy.accept(lastCandles, market, account, exchange);
                        logger.log(lastCandles, account);
                        market.accept(lastCandles);
                        if (!needStop(market.getName(), market.getLastCandles(), account)) {
                            break;
                        }
                    }
                })).collect(Collectors.toList());

        CompletableFuture<Void> oneFuture = CompletableFuture.allOf(taskList.toArray(new CompletableFuture[stockList.size()]));
        oneFuture.get();

        return logger.createReport();
    }

    private boolean needStop(String name, CandlesBO lastCandles, Account account) {
        List<StockBO> holdStockList = account.getHoldStockList(name);
        List<StockBO> filterStockList = Streams.of(holdStockList)
                .filter(this::isNotSpot)
                .collect(Collectors.toList());
        if (Streams.size(filterStockList) != Streams.size(holdStockList)) {
            return false;
        }

        List<StockBO> blowUpStockList = Streams.of(filterStockList)
                .filter(stock -> blowUp(stock, lastCandles.getClose())).collect(Collectors.toList());

        return Streams.size(blowUpStockList) == Streams.size(filterStockList);
    }

    private boolean blowUp(StockBO stock, BigDecimal close) {
        return stock.willBlowUp(close);
    }

    private boolean isSpot(StockBO stockBO) {
        return stockBO.isSpot();
    }

    private boolean isNotSpot(StockBO stockBO) {
        return !isSpot(stockBO);
    }

    private TradeLogger createTradeLogger(Exchange exchange) {
        TradeLogger logger = new TimeBasedLogger();
        exchange.registryLogger(logger);
        return logger;
    }
}
