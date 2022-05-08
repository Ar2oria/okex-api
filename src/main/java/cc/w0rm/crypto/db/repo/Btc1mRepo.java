package cc.w0rm.crypto.db.repo;

import cc.w0rm.crypto.db.domain.Btc1m;
import cc.w0rm.crypto.db.mappter.Btc1mMapper;
import cc.w0rm.crypto.db.mappter.Btc1mMapperExt;
import cc.w0rm.crypto.manager.DbManager;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class Btc1mRepo extends BaseRepo {

    public Btc1mRepo() {
        super();
    }

    public Btc1mRepo(SqlSession session) {
        super(session);
    }

    private Btc1mMapper btc1mMapper = DbManager.getMapper(Btc1mMapper.class, getSqlSession());
    private Btc1mMapperExt btc1mMapperExt = DbManager.getMapper(Btc1mMapperExt.class, getSqlSession());

    public int batchInsertIgnore(List<Btc1m> list) {
        return btc1mMapperExt.batchInsertIgnore(list);
    }

}
