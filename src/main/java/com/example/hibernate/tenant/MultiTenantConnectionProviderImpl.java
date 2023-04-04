package com.example.hibernate.tenant;

import com.example.hibernate.dao.TenantRepository;
import com.example.hibernate.entity.Tenant;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

//    @Autowired
//    private TenantRepository tenantRepository;

    @Override
    protected DataSource selectAnyDataSource() {
        return dataSources.values().iterator().next();
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        DataSource dataSource = dataSources.get(tenantIdentifier);
//        if (dataSource == null) {
//            Tenant tenant = tenantRepository.findByTenantId(tenantIdentifier);
//            if (tenant !=null) {
//                HikariConfig config = new HikariConfig();
//                config.setJdbcUrl("jdbc:mysql://localhost:3306/" + tenant.getName() + "?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai");
//                config.setUsername("root");
//                config.setPassword("123");
//                config.setDriverClassName("com.mysql.cj.jdbc.Driver");
//                config.setMinimumIdle(1);
//                config.setMaximumPoolSize(5);
//                config.setIdleTimeout(60000);
//                config.setConnectionTimeout(30000);
//                dataSource = new HikariDataSource(config);
//                dataSources.put(tenantIdentifier, dataSource);
//                logger.info("Created DataSource for tenant '{}'", tenantIdentifier);
//            } else {
//                logger.warn("Tenant '{}' not found", tenantIdentifier);
//            }
//        }
        return dataSource;
    }
}

