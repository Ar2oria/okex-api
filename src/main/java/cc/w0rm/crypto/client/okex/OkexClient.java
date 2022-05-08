package cc.w0rm.crypto.client.okex;

import cc.w0rm.crypto.biz.BizException;
import cc.w0rm.crypto.client.Client;
import cc.w0rm.crypto.common.DateTimeUtil;
import cc.w0rm.crypto.common.JsonUtil;
import cc.w0rm.crypto.model.dto.BaseResponse;
import cc.w0rm.crypto.model.dto.CandlesDTO;
import cc.w0rm.crypto.model.dto.HistoryCandlesRequestDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@SuppressWarnings("unchecked")
public class OkexClient implements Client {

    private static final String HOST = "https://www.okx.com";

    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
            .addInterceptor(new OkexAuth())
            .connectTimeout(1000L, TimeUnit.MILLISECONDS)
            .build();

    @Override
    public List<CandlesDTO> queryHistoryCandles(HistoryCandlesRequestDTO request) throws Exception {
        Request req = new Request.Builder()
                .get()
                .url(HOST + "/api/v5/market/history-candles?" + request)
                .build();
        String respStr = doGet(req);
        BaseResponse<List<String[]>> response = JsonUtil.fromJson(respStr, new TypeReference<BaseResponse<List<String[]>>>() {
        });
        if (response.getCode() != 0) {
            throw new BizException("请求历史k线数据异常, resp=" + JsonUtil.toJson(response));
        }

        return response.getData().stream()
                .map(CandlesDTO::parse)
                .collect(Collectors.toList());
    }

    private String doGet(Request req) throws IOException {
        Response execute = null;
        try {
            Call call = OK_HTTP_CLIENT.newCall(req);
            execute = call.execute();
            return execute.body().string();
        } finally {
            if (Objects.nonNull(execute) && Objects.nonNull(execute.body())) {
                execute.close();
            }
        }

    }

    static class OkexAuth implements Interceptor {

        static final String ID = "0730e250-9a89-4ea9-8de2-2b9a097c8241";
        static final String SECRET = "0231410415452EA010C6F7ECC7E34E41";
        static final String PWD = "Yh3yxyz...";

        @NotNull
        @Override
        public Response intercept(@NotNull Chain chain) throws IOException {

            String utc = DateTimeUtil.nowUtc();

            Request request = chain.request();
            Headers newHeaders = request.headers().newBuilder()
                    .add("OK-ACCESS-KEY", ID)
                    .add("OK-ACCESS-SIGN", getSign(request, utc))
                    .add("OK-ACCESS-TIMESTAMP", utc)
                    .add("OK-ACCESS-PASSPHRASE", PWD)
                    .add("Content-Type", "application/json")
                    .build();
            Request newRequest = request.newBuilder()
                    .headers(newHeaders)
                    .build();

            return chain.proceed(newRequest);
        }

        static final Mac SHA256;
        static final String HMAC_SHA256 = "HmacSHA256";

        static {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
                SHA256 = Mac.getInstance(HMAC_SHA256);
                SHA256.init(secretKeySpec);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private String getSign(Request request, String utc) throws IOException {
            String data = utc + request.method() + getRequestPath(request);
            if (Objects.nonNull(request.body()) && request.body().contentLength() > 0) {
                data += request.body();
            }
            return calcSign(data);
        }

        private String getRequestPath(Request request) {
            return request.url().url().getFile();
        }

        private String calcSign(String data) {
            byte[] bytes = SHA256.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.encode(bytes);
        }
    }

}
