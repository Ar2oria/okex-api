package cc.w0rm.crypto.model.dto;

import cc.w0rm.crypto.model.enums.Bar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryCandlesRequestDTO {
    private String instId;
    private long after;
    private long before;
    private Bar bar;
    private int limit;

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
