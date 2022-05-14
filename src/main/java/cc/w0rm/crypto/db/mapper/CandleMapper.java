package cc.w0rm.crypto.db.mapper;

import cc.w0rm.crypto.db.domain.Candle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CandleMapper {

    int batchInsertIgnore(@Param("tableName") String tableName, @Param("list") List<Candle> list);

    Candle selectAny(@Param("tableName") String tableName);

    int createTable(@Param("tableName") String tableName);

    List<Candle> selectCandleList(@Param("tableName") String tableName, @Param("nowTs") long nowTs, @Param("size") int size);
}
