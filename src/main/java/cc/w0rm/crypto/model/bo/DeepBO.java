package cc.w0rm.crypto.model.bo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DeepBO {
    private BigDecimal price;
    private BigDecimal cryptoCount;
    private BigDecimal orderCount;
}
