package cc.w0rm.crypto.model.exchange;

import cc.w0rm.crypto.model.bo.CandleBO;
import cc.w0rm.crypto.model.enums.Bar;

import java.util.List;

public interface DataSource {

    int status();

    long getSystemTime();

    List<CandleBO> selectCandleList(String instId, Bar bar, int size);
}
