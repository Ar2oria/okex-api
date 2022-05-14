package cc.w0rm.crypto.db.repo;

import cc.w0rm.crypto.db.domain.Candle;
import cc.w0rm.crypto.db.mapper.CandleMapper;
import cc.w0rm.crypto.manager.DbManager;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CandleRepo extends BaseRepo {

    private CandleMapper candleMapper = DbManager.getMapper(CandleMapper.class, getSqlSession());

    public CandleRepo() {
        super();
    }

    public CandleRepo(SqlSession session) {
        super(session);
    }

    public int batchInsertIgnore(String tableName, List<Candle> list) {
        return candleMapper.batchInsertIgnore(tableName, list);
    }

    public Candle selectAny(String tableName) {
        return candleMapper.selectAny(tableName);
    }

    public int createTable(String tableName) throws Exception {
        return candleMapper.createTable(tableName);
    }

    public List<Candle> selectCandleList(String tableName, long nowTs, int size) {
        return candleMapper.selectCandleList(tableName, nowTs, size);
    }
}
