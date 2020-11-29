package haoyc.assignment_03.context;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomXMLContext {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println(applicationContext.getBean("user"));
        System.out.println(applicationContext.getBean("lesson"));
    }
}
