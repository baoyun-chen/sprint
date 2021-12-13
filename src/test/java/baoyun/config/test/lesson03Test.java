package baoyun.config.test;

import baoyun.lessons.lesson03.MainConfigOfPropertyValues;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class lesson03Test {


    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);
        //printAllComponents(applicationContext);

        Object person = applicationContext.getBean("person");

        System.out.println(person.toString());

        String nickName = applicationContext.getEnvironment().getProperty("nickName");

        System.out.println(nickName);
    }

    public void printAllComponents(AnnotationConfigApplicationContext applicationContext){
        String[] beans = applicationContext.getBeanDefinitionNames();
        for(String name : beans){
            System.out.println(name);
        }
    }
}
