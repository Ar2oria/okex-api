package cc.w0rm.crypto.model.bo;

import cc.w0rm.crypto.common.JsonUtil;
import cc.w0rm.crypto.db.domain.TaskDetail;
import cc.w0rm.crypto.model.enums.Bar;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Data
public class CandlesRequestBO {
    private String instId;
    private Bar bar;
    private long before;
    private long after;
    private int limit = 100;

    public static CandlesRequestBO buildRequest(SaveCryptoConfig config, long start, long end) {
        CandlesRequestBO returnVal = new CandlesRequestBO();
        returnVal.setInstId(config.getInstId());
        returnVal.setBar(config.getBar());
        returnVal.setLimit(config.getLimit());
        returnVal.setBefore(start);
        returnVal.setAfter(end);
        return returnVal;
    }

    public static CandlesRequestBO parse(TaskDetail taskDetail) {
        if (Objects.isNull(taskDetail)) {
            return null;
        }
        String params = taskDetail.getParams();
        if (StringUtils.isBlank(params)) {
            return null;
        }

        return parse(params);
    }

    private static CandlesRequestBO parse(String params) {
        if (StringUtils.isBlank(params)) {
            return null;
        }

        return JsonUtil.fromJson(params, new TypeReference<CandlesRequestBO>() {
        });
    }
}
