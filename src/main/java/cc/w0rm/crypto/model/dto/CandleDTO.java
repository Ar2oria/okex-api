package cc.w0rm.crypto.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CandleDTO {
    private long ts;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private BigDecimal volume;
    private BigDecimal volCcy;

    public static CandleDTO parse(String[] data) {
        CandleDTO candleDTO = new CandleDTO();
        candleDTO.setTs(Long.parseLong(data[0]));
        candleDTO.setOpen(new BigDecimal(data[1]));
        candleDTO.setHigh(new BigDecimal(data[2]));
        candleDTO.setLow(new BigDecimal(data[3]));
        candleDTO.setClose(new BigDecimal(data[4]));
        candleDTO.setVolume(new BigDecimal(data[5]));
        candleDTO.setVolCcy(new BigDecimal(data[6]));

        return candleDTO;
    }
}
