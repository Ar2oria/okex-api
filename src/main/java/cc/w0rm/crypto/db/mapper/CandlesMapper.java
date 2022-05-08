package cc.w0rm.crypto.db.mapper;

import cc.w0rm.crypto.db.domain.Candles;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CandlesMapper {

    int batchInsertIgnore(@Param("tableName") String tableName, @Param("list") List<Candles> list);
}
