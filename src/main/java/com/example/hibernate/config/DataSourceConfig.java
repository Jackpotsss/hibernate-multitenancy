package com.example.hibernate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean("multiDataSources")
    public Map<String, DataSource> dataSources() {
        Map<String, DataSource> dataSources = new HashMap<>();
        dataSourceProperties.getTenants().forEach((tenantId, tenantProperties) -> {
            DataSource dataSource = DataSourceBuilder.create()
                    .url(tenantProperties.getUrl())
                    .username(tenantProperties.getUsername())
                    .password(tenantProperties.getPassword())
                    .build();
            dataSources.put(tenantId, dataSource);
        });
        return dataSources;
    }
}

