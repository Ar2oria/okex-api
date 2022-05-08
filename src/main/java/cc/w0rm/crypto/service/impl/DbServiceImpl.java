package cc.w0rm.crypto.service.impl;

import cc.w0rm.crypto.db.domain.Btc1m;
import cc.w0rm.crypto.db.repo.Btc1mRepo;
import cc.w0rm.crypto.model.bo.SaveCryptoConfig;
import cc.w0rm.crypto.model.dto.CandlesDTO;
import cc.w0rm.crypto.service.DbService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DbServiceImpl implements DbService {


    @Override
    public void saveHistoryCandles(SaveCryptoConfig config, List<CandlesDTO> candlesList) throws Exception {
        if (CollectionUtils.isEmpty(candlesList)) {
            return;
        }

        Btc1mRepo repo = new Btc1mRepo();

        List<Btc1m> data = candlesList.stream()
                .map(this::convert2Btc1m)
                .collect(Collectors.toList());

        int count = repo.batchInsertIgnore(data);

        log.info("insert={}, {}", count, candlesList);
    }

    private Btc1m convert2Btc1m(CandlesDTO candlesDTO) {
        Btc1m btc1m = new Btc1m();
        btc1m.setTs(candlesDTO.getTs());
        btc1m.setO(candlesDTO.getOpen());
        btc1m.setC(candlesDTO.getClose());
        btc1m.setH(candlesDTO.getHigh());
        btc1m.setL(candlesDTO.getLow());
        btc1m.setVol(candlesDTO.getVolume());
        btc1m.setVolccy(candlesDTO.getVolCcy());

        return btc1m;
    }
}
