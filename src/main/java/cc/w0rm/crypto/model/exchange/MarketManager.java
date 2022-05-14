package cc.w0rm.crypto.model.exchange;

import cc.w0rm.crypto.model.bo.BookBO;
import cc.w0rm.crypto.model.bo.CandleBO;
import cc.w0rm.crypto.model.enums.Bar;

import java.util.List;

public interface MarketManager {

    List<CandleBO> getHistoryCandleList(String instId, Bar bar, int size);

    BookBO getBooks(String instId, int size);

}
