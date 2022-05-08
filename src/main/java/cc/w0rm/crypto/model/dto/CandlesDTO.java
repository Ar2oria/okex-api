package cc.w0rm.crypto.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CandlesDTO {
    private long ts;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private BigDecimal volume;
    private BigDecimal volCcy;

    public static CandlesDTO parse(String[] data) {
        CandlesDTO candlesDTO = new CandlesDTO();
        candlesDTO.setTs(Long.parseLong(data[0]));
        candlesDTO.setOpen(new BigDecimal(data[1]));
        candlesDTO.setHigh(new BigDecimal(data[2]));
        candlesDTO.setLow(new BigDecimal(data[3]));
        candlesDTO.setClose(new BigDecimal(data[4]));
        candlesDTO.setVolume(new BigDecimal(data[5]));
        candlesDTO.setVolCcy(new BigDecimal(data[6]));

        return candlesDTO;
    }
}
