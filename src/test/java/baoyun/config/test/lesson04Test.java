package baoyun.config.test;

import baoyun.lessons.lesson04.MainConfigOfAutowired;
import baoyun.lessons.lesson04.MainConfigOfProfile;
import baoyun.lessons.lesson04.beans.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class lesson04Test {


    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);

        BookService bookService = applicationContext.getBean(BookService.class);

        System.out.println(bookService);

        System.out.println(applicationContext);

    }


    @Test
    public void test02(){  //   VM option： -Dspring.profiles.active=dev 设置了激活环境为dev
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);

        String[] beans= applicationContext.getBeanDefinitionNames();

        for(String bean : beans){
            System.out.println(bean);
        }
    }

    @Test
    public void test03(){

        // 用无参构造器创建一个Ioc 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 设置激活的环境
        applicationContext.getEnvironment().setActiveProfiles("test","dev");
        // 注册主配置类
        applicationContext.register(MainConfigOfProfile.class);
        //刷新启动容器
        applicationContext.refresh();

        String[] beans= applicationContext.getBeanDefinitionNames();

        for(String bean : beans){
            System.out.println(bean);
        }
    }
}
