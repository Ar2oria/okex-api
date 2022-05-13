package cc.w0rm.crypto.model.bo;

import cc.w0rm.crypto.model.Entry;
import cc.w0rm.crypto.model.TradeLogger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LoggerRoot {

    private Entry<TradeLogger> entryRoot = new Entry<>();
    private volatile Entry<TradeLogger> entryTail = entryRoot;
    private Lock lock = new ReentrantLock();

    public void addLogger(TradeLogger logger) {
        lock.lock();
        Entry<TradeLogger> entry = new Entry<>();
        entry.setData(logger);
        entryTail.setNext(entry);
        entryTail = entry;
        lock.unlock();
    }
}
