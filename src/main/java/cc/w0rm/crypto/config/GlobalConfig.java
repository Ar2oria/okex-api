package cc.w0rm.crypto.config;

import cc.w0rm.crypto.common.JsonUtil;
import cc.w0rm.crypto.common.ResourceUtil;
import cc.w0rm.crypto.model.bo.OkApiConfig;
import com.fasterxml.jackson.core.type.TypeReference;

public class GlobalConfig {
    public static final long ONE_MILLIS = 1000L;
    public static final long ONE_MIN_MILLIS = 60 * ONE_MILLIS;
    public static final long ONE_HOUR_MILLIS = 60 * ONE_MIN_MILLIS;
    public static final long ONE_DAY_MILLIS = 24 * ONE_HOUR_MILLIS;
    public static final long ONE_WEEK_MILLIS = 7 * ONE_DAY_MILLIS;
    public static final long ONE_MON_MILLIS = 30 * ONE_DAY_MILLIS;
    public static final long ONE_YEAR_MILLIS = 365 * ONE_DAY_MILLIS;

    public static OkApiConfig getOkApiConfig() {
        try {
            String s = ResourceUtil.readResource("okex.json");
            return JsonUtil.fromJson(s, new TypeReference<OkApiConfig>() {
            });
        } catch (Exception e) {
            return null;
        }
    }

}
