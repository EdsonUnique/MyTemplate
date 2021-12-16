package edson.MyTemplate.multiDataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @Description:
 * 动态切换数据源主要依靠 AbstractRoutingDataSource。
 * 创建一个 AbstractRoutingDataSource 的子类，重写 determineCurrentLookupKey 方法，
 * 用于决定使用哪一个数据源。这里主要用到 AbstractRoutingDataSource 的两个属
 * 性 defaultTargetDataSource和targetDataSources。defaultTargetDataSource 默认目标数据源，
 * targetDataSources（map类型）存放用来切换的数据源。
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        // afterPropertiesSet()方法调用时用来将targetDataSources的属性写入resolvedDataSources中的
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSource();
    }
}
