package cc.w0rm.crypto.model.bo;

import cc.w0rm.crypto.common.Streams;
import cc.w0rm.crypto.model.Account;
import cc.w0rm.crypto.model.Exchange;
import cc.w0rm.crypto.model.Market;
import cc.w0rm.crypto.model.TradeLogger;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VmExchange implements Exchange {

    private LoggerRoot loggerRoot = new LoggerRoot();

    private Map<String, Market> marketMap;

    public VmExchange(List<Market> marketList) {
        if (CollectionUtils.isEmpty(marketList)) {
            marketMap = new HashMap<>();
            return;
        }
        marketMap = Streams.of(marketList)
                .collect(Collectors.toMap(Market::getName, Function.identity(), (a, b) -> a));
    }

    @Override
    public void registryLogger(TradeLogger logger) {
        loggerRoot.addLogger(logger);
    }

    @Override
    public boolean buy(Account account, OrderBO order) {
        return false;
    }

    @Override
    public boolean sell(Account account, OrderBO order) {
        return false;
    }

    @Override
    public List<Market> getMarketList(List<String> marketName) {
        if (CollectionUtils.isEmpty(marketName)) {
            return Collections.emptyList();
        }

        return Streams.of(marketName)
                .map(name -> marketMap.get(name))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
