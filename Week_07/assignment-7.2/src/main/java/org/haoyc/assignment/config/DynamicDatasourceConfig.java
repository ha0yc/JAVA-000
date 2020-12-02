package org.haoyc.assignment.config;

import org.haoyc.assignment.datasource.DynamicDatasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DynamicDatasourceConfig {

    @Autowired
    @Qualifier(value = DatasourceConfig.MASTER)
    private DataSource dataSource;

    @Autowired
    @Qualifier(value = DatasourceConfig.SLAVE_1)
    private DataSource slaveDataSource1;

    @Autowired
    @Qualifier(value = DatasourceConfig.SLAVE_2)
    private DataSource slaveDataSource2;

    @Autowired
    @Qualifier(value = DatasourceConfig.SLAVE_3)
    private DataSource slaveDataSource3;


    @Bean("dynamicDatasource")
    public DataSource dynamicDatasource() {
        Map<Object, Object> dataSourceMap = new HashMap<>(4);
        dataSourceMap.put(DatasourceConfig.MASTER, dataSource);
        dataSourceMap.put(DatasourceConfig.SLAVE_1, slaveDataSource1);
        dataSourceMap.put(DatasourceConfig.SLAVE_2, slaveDataSource2);
        dataSourceMap.put(DatasourceConfig.SLAVE_3, slaveDataSource3);
        //设置动态数据源
        DynamicDatasource dynamicDataSource = new DynamicDatasource();
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.setDefaultTargetDataSource(dataSource);

        return dynamicDataSource;
    }
}
