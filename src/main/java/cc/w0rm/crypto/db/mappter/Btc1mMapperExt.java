package cc.w0rm.crypto.db.mappter;

import cc.w0rm.crypto.db.domain.Btc1m;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Btc1mMapperExt {
    int batchInsertIgnore(@Param("list") List<Btc1m> list);
}
