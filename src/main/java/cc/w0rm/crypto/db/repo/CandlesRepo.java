package cc.w0rm.crypto.db.repo;

import cc.w0rm.crypto.db.domain.Candles;
import cc.w0rm.crypto.db.mapper.CandlesMapper;
import cc.w0rm.crypto.manager.DbManager;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CandlesRepo extends BaseRepo {

    private CandlesMapper candlesMapper = DbManager.getMapper(CandlesMapper.class, getSqlSession());

    public CandlesRepo() {
        super();
    }

    public CandlesRepo(SqlSession session) {
        super(session);
    }

    public int batchInsertIgnore(String tableName, List<Candles> list) {
        return candlesMapper.batchInsertIgnore(tableName, list);
    }

}
