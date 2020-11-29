package haoyc.assignment_03.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnoConfigContext {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("haoyc.assignment_03.annotation");
    }
}
