package com.duanzm.framework.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);

    /**
     * 使用ThreadLocal维护变量，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

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
        return getDataSourceType();
    }

    /**
     * 获得数据源的变量
     */
    public static String getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 设置数据源的变量
     */
    public static void setDataSourceType(String dsType) {
        log.info("切换到{}数据源", dsType);
        CONTEXT_HOLDER.set(dsType);
    }

    /**
     * 清空数据源变量
     */
    public static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }
}