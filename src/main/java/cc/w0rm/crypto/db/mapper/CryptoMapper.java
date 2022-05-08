package cc.w0rm.crypto.db.mapper;

import cc.w0rm.crypto.db.domain.Crypto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CryptoMapper {

    int batchInsertIgnore(@Param("tableName") String tableName, @Param("list") List<Crypto> list);
}
