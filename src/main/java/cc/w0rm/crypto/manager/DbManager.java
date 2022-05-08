package cc.w0rm.crypto.manager;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;

import java.util.function.Function;

public class DbManager {

    private static SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
            .build(DbManager.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));
    private static SqlSession sqlSession = sqlSessionFactory.openSession(true);

    public static SqlSession getDefaultSession() {
        return sqlSession;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getMapper(Class<?> clazz, SqlSession sqlSession) {
        return (T) sqlSession.getMapper(clazz);
    }

    public static <R> R openTx(Function<SqlSession, R> function) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession(TransactionIsolationLevel.REPEATABLE_READ);
        try {
            R result = function.apply(sqlSession);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

}
