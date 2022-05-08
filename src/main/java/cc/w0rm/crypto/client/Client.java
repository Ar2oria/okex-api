package cc.w0rm.crypto.client;

import cc.w0rm.crypto.model.dto.CandlesDTO;
import cc.w0rm.crypto.model.dto.HistoryCandlesRequestDTO;

import java.util.List;

public interface Client {

    List<CandlesDTO> queryHistoryCandles(HistoryCandlesRequestDTO request) throws Exception;

}
