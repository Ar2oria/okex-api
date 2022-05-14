package cc.w0rm.crypto.model.exchange.local;

import cc.w0rm.crypto.common.Streams;
import cc.w0rm.crypto.common.TableUtil;
import cc.w0rm.crypto.model.bo.CandleBO;
import cc.w0rm.crypto.model.enums.Bar;
import cc.w0rm.crypto.model.exchange.DataSource;
import cc.w0rm.crypto.service.DbService;
import cc.w0rm.crypto.service.impl.DbServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

public class LocalDataSource implements DataSource {

    private final long beginTs;
    private final long endTs;
    private volatile long nowTs;

    private DbService dbService = new DbServiceImpl();

    public LocalDataSource(long beginTs, long endTs) {
        this.beginTs = beginTs;
        this.endTs = endTs;
        this.nowTs = beginTs;
    }

    @Override
    public int status() {
        return nowTs <= endTs ? 1 : 0;
    }

    @Override
    public long getSystemTime() {
        return 0;
    }

    @Override
    public List<CandleBO> selectCandleList(String instId, Bar bar, int size) {
        String tableName = TableUtil.getTableName(instId, bar);

        return Streams.of(dbService.selectCandleList(tableName, nowTs, size))
                .map(CandleBO::parse)
                .collect(Collectors.toList());
    }

    public void increaseTs() {
        nowTs = Math.min(endTs, nowTs + 1);
    }


}
