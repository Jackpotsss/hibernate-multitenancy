package com.example.hibernate.tenant;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 多租户连接提供器
 *
 * `DataSourceBasedMultiTenantConnectionProviderImpl`继承了`AbstractDataSourceBasedMultiTenantConnectionProviderImpl`，
 * 实现了`selectAnyDataSource`和`selectDataSource`方法。`selectAnyDataSource`方法返回默认数据源，`selectDataSource`方法根据租户ID选择数据源。
 * 在`selectDataSource`方法中，使用`applicationContext.getBean`方法从Spring容器中获取`customerDataSource`，`
 * customerDataSource`是一个动态数据源，根据租户ID动态创建数据源。如果数据源不存在，则抛出`TenantNotFoundException`异常。
 */
@Component
public class MultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(MultiTenantConnectionProviderImpl.class);

    @Autowired
    @Qualifier("multiDataSources")
    private Map<String, DataSource> dataSources ;

    @Override
    protected DataSource selectAnyDataSource() {
        DataSource dataSource = dataSources.values().iterator().next();
        return dataSource;
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        DataSource dataSource = dataSources.get(tenantIdentifier);
        return dataSource;
    }
}

