package org.haoyc.assignment.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:config/jdbc.properties")
public class DatasourceConfig {
    public static final String MASTER = "master";
    public static final String SLAVE = "slave";
    public static final String SLAVE_1 = "slave1";
    public static final String SLAVE_2 = "slave2";
    public static final String SLAVE_3 = "slave3";

    @Bean(MASTER)
    @ConfigurationProperties(prefix = "spring.datasource.master")
    @ConditionalOnProperty(prefix = "spring.datasource.master")
    public DataSource masterDataSource(DataSourceProperties properties){
        return createDatasource(properties);
    }

    private DataSource createDatasource(DataSourceProperties properties) {
        HikariDataSource dataSource = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        if (StringUtils.hasText(properties.getName())) {
            dataSource.setPoolName(properties.getName());
        }
        return dataSource;
    }

    @Bean(SLAVE_1)
    @ConditionalOnProperty(prefix = "spring.datasource.slave1")
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public DataSource slaveDataSource1(DataSourceProperties properties){
        return createDatasource(properties);
    }

    @Bean(SLAVE_2)
    @ConditionalOnProperty(prefix = "spring.datasource.slave2")
    @ConfigurationProperties(prefix = "spring.datasource.slave2")
    public DataSource slaveDataSource2(DataSourceProperties properties){
        return createDatasource(properties);
    }

    @Bean(SLAVE_3)
    @ConditionalOnProperty(prefix = "spring.datasource.slave3")
    @ConfigurationProperties(prefix = "spring.datasource.slave3")
    public DataSource slaveDataSource3(DataSourceProperties properties){
        return createDatasource(properties);
    }


}
