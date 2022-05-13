package cc.w0rm.crypto.service.impl;

import cc.w0rm.crypto.client.okex.OkexClient;
import cc.w0rm.crypto.common.Streams;
import cc.w0rm.crypto.model.bo.CandlesBO;
import cc.w0rm.crypto.model.bo.CandlesRequestBO;
import cc.w0rm.crypto.model.dto.HistoryCandlesRequestDTO;
import cc.w0rm.crypto.service.ClientService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClientServiceImpl implements ClientService {

    private OkexClient okexClient = new OkexClient();

    @Override
    public List<CandlesBO> queryHistoryCandles(CandlesRequestBO candlesRequestBO) throws Exception {
        if (Objects.isNull(candlesRequestBO)) {
            return Collections.emptyList();
        }

        HistoryCandlesRequestDTO requestDTO = HistoryCandlesRequestDTO.parse(candlesRequestBO);
        return Streams.of(okexClient.queryHistoryCandles(requestDTO))
                .map(CandlesBO::parse)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
