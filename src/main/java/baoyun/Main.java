package baoyun;

import baoyun.lessons.lesson01.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] arg){
        AnnotationConfigApplicationContext application = new AnnotationConfigApplicationContext(MainConfig.class);
       // Cat cat = application.getBean(Cat.class);
        for(String name : application.getBeanDefinitionNames()){
            System.out.println(name);
        }
    }
}
