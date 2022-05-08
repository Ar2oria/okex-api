package cc.w0rm.crypto.common;

import cc.w0rm.crypto.model.dto.HistoryCandlesRequestDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {

    private static final JsonMapper OBJECT_MAPPER = new JsonMapper();

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
    }

    public static String toJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            log.error("json 序列化失败！", e);
            throw new RuntimeException(e);
        }

    }

    public static <T> T fromJson(String json, TypeReference<T> tTypeReference) {
        try {
            return OBJECT_MAPPER.readValue(json, tTypeReference);
        } catch (Exception e) {
            log.error("json 反序列化失败！", e);
            throw new RuntimeException(e);
        }

    }

}
