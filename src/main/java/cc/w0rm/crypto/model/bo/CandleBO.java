package cc.w0rm.crypto.model.bo;

import cc.w0rm.crypto.db.domain.Candle;
import cc.w0rm.crypto.model.dto.CandleDTO;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Data
public class CandleBO {
    private long ts;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private BigDecimal volume;
    private BigDecimal volCcy;

    public static CandleBO parse(Candle candle) {
        return getCandleBO(candle.getTs(), candle.getO(), candle.getH(), candle.getL(), candle.getC(), candle.getVol(), candle.getVolCcy());
    }

    public static CandleBO parse(CandleDTO candleDTO) {
        return getCandleBO(candleDTO.getTs(), candleDTO.getOpen(), candleDTO.getHigh(), candleDTO.getLow(), candleDTO.getClose(), candleDTO.getVolume(), candleDTO.getVolCcy());
    }

    public static CandleBO clone(CandleBO candleBO) {
        return getCandleBO(candleBO.getTs(), candleBO.getOpen(), candleBO.getHigh(), candleBO.getLow(), candleBO.getClose(), candleBO.getVolume(), candleBO.getVolCcy());
    }

    @NotNull
    private static CandleBO getCandleBO(long ts, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, BigDecimal volume, BigDecimal volCcy) {
        CandleBO returnVal = new CandleBO();
        returnVal.setTs(ts);
        returnVal.setOpen(open);
        returnVal.setHigh(high);
        returnVal.setLow(low);
        returnVal.setClose(close);
        returnVal.setVolume(volume);
        returnVal.setVolCcy(volCcy);
        return returnVal;
    }
}
