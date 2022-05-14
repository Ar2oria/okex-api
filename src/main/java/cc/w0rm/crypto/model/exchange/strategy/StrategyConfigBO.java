package cc.w0rm.crypto.model.exchange.strategy;

import lombok.Data;
import lombok.Getter;

@Data
public class StrategyConfigBO {

    private String name;
    private Env env;


    @Getter
    enum Env {
        ONLINE,
        TEST,
        LOCAL

    }
}
