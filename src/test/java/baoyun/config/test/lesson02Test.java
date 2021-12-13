package baoyun.config.test;

import baoyun.lessons.lesson02.MainConfigOfLifeCycle;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class lesson02Test {

    @Test
    public void test1(){

        // 1. 创建IoC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("容器创建完成。。。");
        //printAllComponents(applicationContext);
        applicationContext.close();
        System.out.println("容器销毁完成。。。");


    }

    public void printAllComponents(AnnotationConfigApplicationContext applicationContext){
        String[] beans = applicationContext.getBeanDefinitionNames();
        for(String name : beans){
            System.out.println(name);
        }
    }
}
