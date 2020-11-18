package haoyc.assignment_03.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public User user(){
        User user = new User(1, "haoyc");
        user.setLesson(lesson());
        return user;
    }

    @Bean
    public Lesson lesson(){
        return new Lesson(1, "java");
    }
}
