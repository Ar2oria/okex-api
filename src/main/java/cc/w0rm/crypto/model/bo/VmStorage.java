package cc.w0rm.crypto.model.bo;

import cc.w0rm.crypto.model.Storage;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class VmStorage implements Storage {

    private ConcurrentHashMap<String, StockBO> storageMap = new ConcurrentHashMap<>();

    @Override
    public boolean hasStock(String tradeItemName) {
        return storageMap.containsKey(tradeItemName);
    }

    @Override
    public List<StockBO> getStockList(String tradeItemName) {
        return null;
    }

    @Override
    public List<StockBO> getStockList() {
        return null;
    }
}
