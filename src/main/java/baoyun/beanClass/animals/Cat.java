package baoyun.beanClass.animals;

import org.springframework.stereotype.Component;

@Component
public class Cat {

    private String name;

    public Cat() {
        System.out.println("cat constructor");
    }

    public Cat(String name) {
        System.out.println("cat constructor");
        this.name = name;
    }

    public void init(){
        System.out.println("cat init");
    }

    public void destroy(){
        System.out.println("cat destory");
    }


}
