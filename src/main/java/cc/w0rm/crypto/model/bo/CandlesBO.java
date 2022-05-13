package cc.w0rm.crypto.model.bo;

import cc.w0rm.crypto.model.dto.CandlesDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
public class CandlesBO {
    private long ts;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private BigDecimal volume;
    private BigDecimal volCcy;

    public static CandlesBO parse(CandlesDTO candlesDTO) {
        if (Objects.isNull(candlesDTO)) {
            return null;
        }

        CandlesBO returnVal = new CandlesBO();
        returnVal.setTs(candlesDTO.getTs());
        returnVal.setOpen(candlesDTO.getOpen());
        returnVal.setHigh(candlesDTO.getHigh());
        returnVal.setLow(candlesDTO.getLow());
        returnVal.setClose(candlesDTO.getClose());
        returnVal.setVolume(candlesDTO.getVolume());
        returnVal.setVolCcy(candlesDTO.getVolCcy());

        return returnVal;
    }
}
