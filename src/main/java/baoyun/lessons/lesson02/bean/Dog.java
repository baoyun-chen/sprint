package baoyun.lessons.lesson02.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.BeanValidationPostProcessor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Dog implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public Dog() {
        System.out.println("Dog....Constructor");
    }

    @PostConstruct  // 对象创建并赋值之后使用
    public void postInit(){
        System.out.println("Dog....postInit");
    }


    @PreDestroy  // 对象销毁之前调用
    public void preDestroy(){
        System.out.println("Dog....preDestroy");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
