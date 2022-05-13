package cc.w0rm.crypto.model.bo;

import cc.w0rm.crypto.common.Streams;
import cc.w0rm.crypto.model.Market;

import java.util.*;

public class VmMarket implements Market {

    private String name;
    private Deque<CandlesBO> queue;
    private List<CandlesBO> historyCandles;

    public VmMarket(String name, List<CandlesBO> initData) {
        this.name = name;
        this.queue = new LinkedList<>(initData);
        this.historyCandles = new ArrayList<>(this.queue.size());
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean hasMore() {
        return !queue.isEmpty();
    }

    @Override
    public CandlesBO getLastCandles() {
        CandlesBO peek;
        if (hasMore() && Objects.nonNull((peek = queue.peek()))) {
            return peek;
        }

        throw new IllegalStateException("can not get last price, queue is empty!");
    }

    @Override
    public void accept(CandlesBO candles) {
        if (!hasMore()) {
            return;
        }
        CandlesBO peek = queue.peekFirst();
        if (Objects.isNull(peek) || peek.getTs() != candles.getTs()) {
            return;
        }

        historyCandles.add(peek);
        queue.pollFirst();
    }

    @Override
    public List<CandlesBO> getHistoryCandlesBO(long size) {
        long maxLen = Math.min(size, Streams.size(historyCandles));
        if (size >= maxLen) {
            return new ArrayList<>(historyCandles);
        }

        return historyCandles.subList((int) (maxLen - size), (int) maxLen);
    }

}
