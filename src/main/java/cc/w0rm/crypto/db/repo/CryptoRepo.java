package cc.w0rm.crypto.db.repo;

import cc.w0rm.crypto.db.domain.Crypto;
import cc.w0rm.crypto.db.mapper.CryptoMapper;
import cc.w0rm.crypto.manager.DbManager;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CryptoRepo extends BaseRepo {

    private CryptoMapper cryptoMapper = DbManager.getMapper(CryptoMapper.class, getSqlSession());

    public CryptoRepo() {
        super();
    }

    public CryptoRepo(SqlSession session) {
        super(session);
    }

    public int batchInsertIgnore(String tableName, List<Crypto> list) {
        return cryptoMapper.batchInsertIgnore(tableName, list);
    }

}
