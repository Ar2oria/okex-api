package cc.w0rm.crypto.model.exchange.local;

import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class LocalAccountInitBO {
    /**
     * 账号id
     */
    private String accountId;

    /**
     * 法定货币余额
     */
    private Map<String, BigDecimal> legalTenderMap;

    /**
     * 现货交易手续费
     */
    private Pair<BigDecimal, BigDecimal> spotFee;

    /**
     * 期货交易手续费
     */
    private Pair<BigDecimal, BigDecimal> futuresFee;
}
