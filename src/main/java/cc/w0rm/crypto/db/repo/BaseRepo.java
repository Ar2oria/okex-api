package cc.w0rm.crypto.db.repo;

import cc.w0rm.crypto.manager.DbManager;
import lombok.Data;
import org.apache.ibatis.session.SqlSession;

import java.util.Objects;

@Data
public class BaseRepo {
    private SqlSession sqlSession;

    public BaseRepo() {
        sqlSession = DbManager.getDefaultSession();
    }

    public BaseRepo(SqlSession session) {
        if (Objects.isNull(session)) {
            throw new IllegalArgumentException("sql session is null!");
        }
        this.sqlSession = session;
    }
}
