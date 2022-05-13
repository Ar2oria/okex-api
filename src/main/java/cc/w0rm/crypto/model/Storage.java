package cc.w0rm.crypto.model;

import cc.w0rm.crypto.model.bo.StockBO;

import java.util.List;

public interface Storage {

    boolean hasStock(String tradeItemName);

    List<StockBO> getStockList(String tradeItemName);

    List<StockBO> getStockList();
}
