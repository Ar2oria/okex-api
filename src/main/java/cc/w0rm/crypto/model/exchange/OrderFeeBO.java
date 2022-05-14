package cc.w0rm.crypto.model.exchange;

import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;

@Data
public class OrderFeeBO {

    private BigDecimal asks;
    private BigDecimal bids;

    public static OrderFeeBO parse(Pair<BigDecimal, BigDecimal> spotFee) {
        OrderFeeBO returnVal = new OrderFeeBO();
        returnVal.setAsks(spotFee.getLeft());
        returnVal.setBids(spotFee.getRight());

        return returnVal;
    }
}
