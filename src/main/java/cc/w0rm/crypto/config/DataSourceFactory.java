package cc.w0rm.crypto.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

public class DataSourceFactory extends UnpooledDataSourceFactory {
    public DataSourceFactory() {
        this.dataSource = new ComboPooledDataSource();
    }
}
