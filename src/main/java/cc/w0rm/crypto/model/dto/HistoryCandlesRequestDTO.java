package cc.w0rm.crypto.model.dto;

import cc.w0rm.crypto.model.bo.CandlesRequestBO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryCandlesRequestDTO {
    private String instId;
    private long after;
    private long before;
    private String bar;
    private int limit;

    public static HistoryCandlesRequestDTO parse(CandlesRequestBO candlesRequestBO) {
        HistoryCandlesRequestDTO returnVal = new HistoryCandlesRequestDTO();
        returnVal.setInstId(candlesRequestBO.getInstId());
        returnVal.setAfter(candlesRequestBO.getAfter());
        returnVal.setBefore(candlesRequestBO.getBefore());
        returnVal.setBar(candlesRequestBO.getBar().getSimple());
        returnVal.setLimit(candlesRequestBO.getLimit());

        return returnVal;
    }

    @Override
    public String toString() {
        String str = "instId=" + instId;
        if (after > 0) {
            str += "&after=" + after;
        }
        if (before > 0) {
            str += "&before=" + before;
        }
        str += "&bar=" + bar;
        str += "&limit=" + limit;

        return str;
    }
}
