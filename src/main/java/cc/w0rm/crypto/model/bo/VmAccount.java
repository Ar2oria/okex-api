package cc.w0rm.crypto.model.bo;

import cc.w0rm.crypto.model.Account;
import cc.w0rm.crypto.model.OrderManager;
import cc.w0rm.crypto.model.Storage;

import java.util.List;

public class VmAccount implements Account {

    private String accountId;
    private Storage storage = new VmStorage();
    private OrderManager orderManager = new VmOrderManager();

    public boolean hasHold(String tradeItemName) {
        return storage.hasStock(tradeItemName);
    }

    @Override
    public List<StockBO> getHoldStockList() {
        return storage.getStockList();
    }

    @Override
    public List<StockBO> getHoldStockList(String name) {
        return null;
    }
}
