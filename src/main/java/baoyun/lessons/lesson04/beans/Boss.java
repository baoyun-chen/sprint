package baoyun.lessons.lesson04.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import baoyun.lessons.lesson04.beans.Car;

@Component
public class Boss {

    private Car car;


    public Car getCar() {
        return car;
    }

   // @Autowired // 从容器中获取
    public void setCar(Car car) {
        this.car = car;
    }

    @Autowired // 从容器中获取， 可以省略
    public Boss(Car car) {
        this.car = car;
    }

    /**
     *    // 从容器中获取， @Autowired可以省略
     *     public Boss(@Autowired Car car) {
     *         this.car = car;
     *     }
     */
}
