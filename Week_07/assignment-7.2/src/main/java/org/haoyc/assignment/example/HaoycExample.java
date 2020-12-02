package org.haoyc.assignment.example;

import org.haoyc.assignment.handler.AbstractShardingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class HaoycExample extends AbstractShardingHandler {
    @Autowired
    @Qualifier(value = "dynamicDatasource")
    private DataSource dataSource;

    public void test() {
        //do db operations
        //able to access datasource from customized dynamic datasource
    }

}
