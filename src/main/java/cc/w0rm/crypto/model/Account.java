package cc.w0rm.crypto.model;

import cc.w0rm.crypto.model.bo.StockBO;

import java.util.List;

public interface Account {

    boolean hasHold(String tradeItemName);

    List<StockBO> getHoldStockList();

    List<StockBO> getHoldStockList(String name);
}
