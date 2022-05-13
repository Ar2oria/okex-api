package cc.w0rm.crypto.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
public class StockBO {
    /**
     * 交易对id
     */
    private String instId;
    /**
     * 股票类型
     */
    private StockType stockType;
    /**
     * 交易方向
     */
    private Direction direction;
    /**
     * 开仓价格
     */
    private BigDecimal openPrice;
    /**
     * 杠杆数
     */
    private BigDecimal leverage;
    /**
     * 名义价值
     */
    private BigDecimal nominalValue;
    /**
     * 开仓数量 = 名义价值 / 开仓价格
     */
    private BigDecimal count;
    /**
     * 初始保证金 = 名义价值  / 杠杆数
     */
    private BigDecimal initialMargin;
    /**
     * 维持保证金率
     */
    private BigDecimal maintenanceMarginRate;
    /**
     * 维持保证金速减数
     */
    private BigDecimal marginFastDeductAmount;
    /**
     * 维持保证金金额 = 名义价值 * 维持保证金率 - 维持保证金速减数
     */
    private BigDecimal maintenanceMargin;


    public boolean willBlowUp(BigDecimal lastPrice) {
        if (isSpot()) {
            return false;
        }

        BigDecimal diff;
        if (Direction.LONG.equals(direction)) {
            diff = lastPrice.subtract(openPrice);
        } else {
            diff = openPrice.subtract(lastPrice);
        }

        return initialMargin.add(diff.multiply(count)).compareTo(maintenanceMargin) >= 0;
    }

    public String getMarketName() {
        return instId + "-" + stockType.getSimple();
    }


    @Getter
    @AllArgsConstructor
    enum TradeType {
        TAKER,
        SELLER,

        ;
    }

    @Getter
    @AllArgsConstructor
    enum Direction {

        LONG,
        SHORT,

        ;

    }

    @Getter
    @AllArgsConstructor
    enum StockType {
        SPOT("SPOT"),

        ;

        private String simple;

        public String toString() {
            return simple;
        }
    }

    public boolean isSpot() {
        return StockType.SPOT.equals(stockType);
    }
}
