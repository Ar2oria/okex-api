package cc.w0rm.crypto.service;

import cc.w0rm.crypto.model.exchange.DataSource;
import cc.w0rm.crypto.model.exchange.Exchange;

public interface ExchangeService {

    Exchange creatExchange(DataSource dataSource);

}
