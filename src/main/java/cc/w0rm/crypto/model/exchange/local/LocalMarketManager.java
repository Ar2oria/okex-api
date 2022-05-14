package cc.w0rm.crypto.model.exchange.local;

import cc.w0rm.crypto.common.DateTimeUtil;
import cc.w0rm.crypto.model.bo.BookBO;
import cc.w0rm.crypto.model.bo.CandleBO;
import cc.w0rm.crypto.model.bo.DeepBO;
import cc.w0rm.crypto.model.enums.Bar;
import cc.w0rm.crypto.model.exchange.DataSource;
import cc.w0rm.crypto.model.exchange.MarketManager;
import org.apache.commons.collections.CollectionUtils;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LocalMarketManager implements MarketManager {

    private static final BigDecimal BOOK_PRICE_SPREAD = BigDecimal.valueOf(0.005D);
    private DataSource dataSource;

    public LocalMarketManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<CandleBO> getHistoryCandleList(String instId, Bar bar, int size) {
        Bar c1m = Bar.C_1M;
        long c1mMillis = c1m.getMillis();
        long barMillis = bar.getMillis();
        long rate = barMillis / c1mMillis;

        long realSize = rate * size;


        List<CandleBO> candleList = dataSource.selectCandleList(instId, c1m, (int) realSize);
        if (CollectionUtils.isEmpty(candleList)) {
            return Collections.emptyList();
        }

        List<CandleBO> returnVal = new ArrayList<>(size);
        CandleBO c = null;
        for (int i = 0; i < candleList.size(); ) {
            CandleBO candleBO = candleList.get(i);
            long ts = candleBO.getTs();
            long startOfTs = ts;
            if (i != candleList.size() - 1) {
                startOfTs = DateTimeUtil.getStartOfTs(ts, bar);
            }

            if (Objects.isNull(c)) {
                c = CandleBO.clone(candleBO);
                c.setTs(startOfTs);
            }

            if (startOfTs > c.getTs() || i == candleList.size() - 1) {
                returnVal.add(c);
                c = null;
                continue;
            }

            c.setClose(candleBO.getClose());
            c.setHigh(c.getHigh().max(candleBO.getHigh()));
            c.setLow(c.getLow().min(candleBO.getLow()));
            c.setVolume(c.getVolume().add(candleBO.getVolume()));
            c.setVolCcy(c.getVolCcy().add(candleBO.getVolCcy()));
        }

        return returnVal;
    }


    @Override
    public BookBO getBooks(String instId, int size) {
        List<CandleBO> c1mCandleList = dataSource.selectCandleList(instId, Bar.C_1M, 1);
        if (CollectionUtils.isEmpty(c1mCandleList)) {
            return BookBO.simple();
        }
        List<CandleBO> c1HCandleList = dataSource.selectCandleList(instId, Bar.C_1H, 1);
        if (CollectionUtils.isEmpty(c1HCandleList)) {
            return BookBO.simple();
        }

        CandleBO c1m = c1mCandleList.get(0);
        CandleBO c1h = c1HCandleList.get(0);

        BigDecimal close = c1m.getClose();
        BigDecimal high = c1h.getHigh();
        BigDecimal low = c1h.getLow();
        BigDecimal volCcy = c1h.getVolCcy();

        BigDecimal spread = close.multiply(BOOK_PRICE_SPREAD).divide(BigDecimal.valueOf(2));
        BigDecimal ask1 = close.add(spread);
        BigDecimal bid1 = close.subtract(spread);
        BigDecimal askCount = volCcy.divide(BigDecimal.valueOf(2));
        BigDecimal bidCount = askCount;

        List<DeepBO> askDeepList = new ArrayList<>(size);
        List<DeepBO> bidDeepList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            DeepBO ask = createAskDeep(ask1, high, askCount, i, size);
            DeepBO bid = createBidDeep(bid1, low, bidCount, i, size);

            askDeepList.add(ask);
            bidDeepList.add(bid);
        }

        BookBO returnVal = new BookBO();
        returnVal.setTs(dataSource.getSystemTime());
        returnVal.setAsks(askDeepList);
        returnVal.setBids(bidDeepList);

        return returnVal;
    }

    private DeepBO createAskDeep(BigDecimal ask1, BigDecimal high, BigDecimal askCount, int i, int size) {
        BigDecimal subtract = high.subtract(ask1);
        BigDecimal spread = subtract.divide(BigDecimal.valueOf(size));
        BigDecimal price = ask1.add(spread.multiply(BigDecimal.valueOf(i)));

        BigDecimal cryptoCount = calcCryptDeepCount(askCount, i, size);

        DeepBO returnVal = new DeepBO();
        returnVal.setPrice(price);
        returnVal.setCryptoCount(cryptoCount);
        returnVal.setOrderCount(BigDecimal.valueOf(i));

        return returnVal;
    }

    private DeepBO createBidDeep(BigDecimal ask1, BigDecimal low, BigDecimal bidCount, int i, int size) {
        BigDecimal subtract = ask1.subtract(low);
        BigDecimal spread = subtract.divide(BigDecimal.valueOf(size));
        BigDecimal price = ask1.subtract(spread.multiply(BigDecimal.valueOf(i)));

        BigDecimal cryptoCount = calcCryptDeepCount(bidCount, i, size);

        DeepBO returnVal = new DeepBO();
        returnVal.setPrice(price);
        returnVal.setCryptoCount(cryptoCount);
        returnVal.setOrderCount(BigDecimal.valueOf(i));

        return returnVal;
    }

    @NotNull
    private BigDecimal calcCryptDeepCount(BigDecimal bidCount, int i, int size) {
        BigDecimal oneCount = bidCount.divide(BigDecimal.valueOf(size));
        BigDecimal maxCount = oneCount.multiply(BigDecimal.valueOf(2));
        return BigDecimal.valueOf(i).divide(BigDecimal.valueOf(size)).multiply(maxCount);
    }


}
