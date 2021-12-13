package baoyun.config.test;

import baoyun.lessons.lesson01.MainConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class lesson01Test {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

        String[] definedBeanName = applicationContext.getBeanDefinitionNames();

        for(String name : definedBeanName){
            System.out.println(name);
        }

        Object cat = applicationContext.getBean("cat2");
        Object cat2 = applicationContext.getBean("cat2");
    }




    @Test
    public void test03(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        printAllComponents(applicationContext);

        // 虽然拿的是FactoryBean, 但实际拿到的是 getObject 里面返回的
        Object beanFactory = applicationContext.getBean("factoryBean");
        System.out.println("beanFactory 的类型是" + beanFactory.getClass());

        // 在id前加了一个"&"，拿到的就是工厂bean了
        Object beanFactory1 = applicationContext.getBean("&factoryBean");
        System.out.println("beanFactory1 的类型是" + beanFactory1.getClass());

    }


    public void printAllComponents(AnnotationConfigApplicationContext applicationContext){
        String[] beans = applicationContext.getBeanDefinitionNames();
        for(String name : beans){
            System.out.println(name);
        }
    }

}
