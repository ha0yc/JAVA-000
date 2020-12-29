package io.haoyc.dubbo.hmily.transaction;

import com.alibaba.fastjson.JSON;
import io.haoyc.dubbo.hmily.api.service.AccountService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "io.haoyc")
@DubboComponentScan(basePackages = "io.haoyc")
@SpringBootConfiguration
@SpringBootApplication(exclude = JtaAutoConfiguration.class)
public class MainApplication {
    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:12346")
    AccountService accountService;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return (a) -> {
            System.out.println(JSON.toJSONString(accountService.list(null)));
        };
    }
}
