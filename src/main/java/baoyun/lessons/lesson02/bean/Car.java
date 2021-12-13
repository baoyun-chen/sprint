package baoyun.lessons.lesson02.bean;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Car implements InitializingBean, DisposableBean {
    public Car(){
        System.out.println("Car construct。。。");
    }

    public void init(){
        System.out.println("Car初始化。。。");
    }

    public void destroy(){
        System.out.println("Car销毁。。。");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Car。。。afterPropertiesSet");
    }


}
