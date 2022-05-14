package cc.w0rm.crypto.model.exchange.strategy;


import cc.w0rm.crypto.model.exchange.DataSource;
import cc.w0rm.crypto.model.exchange.Exchange;
import cc.w0rm.crypto.model.exchange.local.LocalDataSource;
import cc.w0rm.crypto.model.exchange.log.TradeLogger;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public abstract class BaseStrategy implements TradeStrategy {

    protected abstract void onSignal(long now, Exchange exchange);

    @Override
    public void invoke() {
        StrategyConfigBO strategyConfig = getStrategyConfig();
        if (Objects.isNull(strategyConfig)) {
            log.error("strategy config is null!");
        }

        DataSource dataSource = createDataSource(strategyConfig);
        Exchange exchange = createExchange(dataSource, strategyConfig);
        TradeLogger logger = createLogger(exchange, strategyConfig);

        for (; ; ) {
            try {
                if (dataSource.status() <= 0) {
                    log.error("策略：{}, datasource 状态异常，策略退出!", strategyConfig.getName());
                    break;
                }
                long systemTime = dataSource.getSystemTime();
                onSignal(systemTime, exchange);
                logger.log();
                updateDatasource(dataSource);
            } catch (Exception e) {
                log.error("策略：{}, 执行中发生异常!", strategyConfig.getName(), e);
            }
        }

    }

    protected TradeLogger createLogger(Exchange dataSource, StrategyConfigBO strategyConfig) {
        return null;
    }

    protected void updateDatasource(DataSource dataSource) {
        if (dataSource instanceof LocalDataSource) {
            ((LocalDataSource) dataSource).increaseTs();
        }
    }

    protected Exchange createExchange(DataSource dataSource, StrategyConfigBO strategyConfig) {
        return null;
    }

    protected DataSource createDataSource(StrategyConfigBO strategyConfig) {
        return null;
    }

}
