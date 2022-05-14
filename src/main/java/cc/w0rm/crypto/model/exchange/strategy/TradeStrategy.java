package cc.w0rm.crypto.model.exchange.strategy;

public interface TradeStrategy {

    StrategyConfigBO getStrategyConfig();

    void invoke();
}
