package org.haoyc.configuration;

import org.haoyc.Student;
import org.haoyc.properties.StudentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnClass(Student.class)
@EnableConfigurationProperties(StudentProperties.class)
public class HaoycStarterEnableAutoConfiguration {

    private final StudentProperties studentProperties;

    @Autowired
    public HaoycStarterEnableAutoConfiguration(StudentProperties studentProperties) {
        this.studentProperties = studentProperties;
    }

    @Bean
    @ConditionalOnProperty(prefix = "haoyc.student", name = "enable", havingValue = "true")
    public Student student() {
        return new Student(Integer.valueOf(studentProperties.getId()), studentProperties.getName());
    }
}
