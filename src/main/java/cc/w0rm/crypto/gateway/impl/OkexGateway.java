package cc.w0rm.crypto.gateway.impl;

import cc.w0rm.crypto.client.impl.OkexClient;
import cc.w0rm.crypto.common.Name;
import cc.w0rm.crypto.common.Streams;
import cc.w0rm.crypto.config.ResourceNameConfig;
import cc.w0rm.crypto.gateway.CryptoGateway;
import cc.w0rm.crypto.model.bo.CandleBO;
import cc.w0rm.crypto.model.bo.CandlesRequestBO;
import cc.w0rm.crypto.model.dto.HistoryCandlesRequestDTO;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Name(ResourceNameConfig.OKEX)
public class OkexGateway implements CryptoGateway {

    private OkexClient okexClient = new OkexClient();

    @Override
    public List<CandleBO> queryHistoryCandles(CandlesRequestBO candlesRequestBO) throws Exception {
        if (Objects.isNull(candlesRequestBO)) {
            return Collections.emptyList();
        }

        HistoryCandlesRequestDTO requestDTO = HistoryCandlesRequestDTO.parse(candlesRequestBO);
        return Streams.of(okexClient.queryHistoryCandles(requestDTO))
                .map(CandleBO::parse)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
