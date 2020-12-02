package org.haoyc.assignment.example;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Service
public class SsExample {
    @Resource
    private DataSource dataSource;
    
    public void test() {
        //do db operations
        //able to access datasource from customized dynamic datasource
    }
    
}
