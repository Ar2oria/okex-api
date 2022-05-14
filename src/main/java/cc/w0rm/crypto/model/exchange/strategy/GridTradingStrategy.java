package cc.w0rm.crypto.model.exchange.strategy;

import cc.w0rm.crypto.model.bo.CandleBO;
import cc.w0rm.crypto.model.enums.Bar;
import cc.w0rm.crypto.model.exchange.Exchange;

import java.util.List;

public class GridTradingStrategy extends BaseStrategy {


    @Override
    public StrategyConfigBO getStrategyConfig() {
        return null;
    }


    @Override
    protected void onSignal(long now, Exchange exchange) {

        List<CandleBO> candleList = exchange.getHistoryCandleList("BTC-USDT", Bar.C_15M, 2000);



    }
}
