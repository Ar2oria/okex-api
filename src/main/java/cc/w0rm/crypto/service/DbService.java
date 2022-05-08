package cc.w0rm.crypto.service;

import cc.w0rm.crypto.model.bo.SaveCryptoConfig;
import cc.w0rm.crypto.model.dto.CandlesDTO;

import java.util.List;

public interface DbService {
    void saveHistoryCandles(SaveCryptoConfig config, List<CandlesDTO> candlesList) throws Exception;
}
