package cc.w0rm.crypto.model.bo;

import cc.w0rm.crypto.model.Account;
import cc.w0rm.crypto.model.Exchange;
import cc.w0rm.crypto.model.TradeStrategy;
import lombok.Data;

@Data
public class VmTaskBO {
    private Account account;
    private Exchange exchange;
    private TradeStrategy tradeStrategy;
}
