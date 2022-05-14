package cc.w0rm.crypto.gateway;

import cc.w0rm.crypto.model.bo.CandleBO;
import cc.w0rm.crypto.model.bo.CandlesRequestBO;

import java.util.List;

public interface CryptoGateway {

    List<CandleBO> queryHistoryCandles(CandlesRequestBO candlesRequestBO) throws Exception;

}
