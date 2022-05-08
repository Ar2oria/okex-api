package cc.w0rm.crypto.db.mappter;

import cc.w0rm.crypto.db.domain.Btc1m;
import cc.w0rm.crypto.db.domain.Btc1mExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Btc1mMapper {
    long countByExample(Btc1mExample example);

    int deleteByExample(Btc1mExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Btc1m row);

    int insertSelective(Btc1m row);

    List<Btc1m> selectByExample(Btc1mExample example);

    Btc1m selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Btc1m row, @Param("example") Btc1mExample example);

    int updateByExample(@Param("row") Btc1m row, @Param("example") Btc1mExample example);

    int updateByPrimaryKeySelective(Btc1m row);

    int updateByPrimaryKey(Btc1m row);
}