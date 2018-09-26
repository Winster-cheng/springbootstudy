package com.mhc.bi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author baiyan
 * @date 2018/09/26
 * @description
 */
@Configuration
public class DataSourceConfig {
    //配置数据源
    @Bean(name = "first")
    @ConfigurationProperties(prefix = "first.datasource") // application.properteis中对应属性的前缀
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "second")
    @ConfigurationProperties(prefix = "second.datasource") // application.properteis中对应属性的前缀
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }
}