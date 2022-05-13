package cc.w0rm.crypto.service;

import cc.w0rm.crypto.model.bo.CandlesBO;
import cc.w0rm.crypto.model.bo.CandlesRequestBO;

import java.util.List;

public interface ClientService {
    List<CandlesBO> queryHistoryCandles(CandlesRequestBO candlesRequestBO) throws Exception;
}
