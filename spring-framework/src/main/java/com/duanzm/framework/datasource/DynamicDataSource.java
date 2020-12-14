package com.duanzm.framework.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 动态数据源
 *
 * 实现逻辑：
 * 1、定义DynamicDataSource类继承抽象类AbstractRoutingDataSource，并实现了determineCurrentLookupKey()方法。
 * 2、把配置的多个数据源会放在AbstractRoutingDataSource的 targetDataSources 和 defaultTargetDataSource 中，
 *    然后通过afterPropertiesSet()方法将数据源分别进行复制到resolvedDataSources和resolvedDefaultDataSource中。
 * 3、调用AbstractRoutingDataSource的 getConnection() 的方法的时候，先调用determineTargetDataSource()方法返回DataSource在进行getConnection()
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     *
     * @param defaultTargetDataSource 默认数据源
     * @param targetDataSources       目标数据源
     */
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }

}