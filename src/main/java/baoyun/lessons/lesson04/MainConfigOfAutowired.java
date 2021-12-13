package baoyun.lessons.lesson04;

import baoyun.lessons.lesson04.beans.BookDao;
import baoyun.lessons.lesson04.beans.Boss;
import baoyun.lessons.lesson04.beans.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@ComponentScan({"baoyun.lessons.lesson04.beans"})
@Configuration
public class MainConfigOfAutowired {

    @Bean("bookDao1")
    @Primary  // 有多个 BookDao 类型的bean, 没有指定的情况下，优先注入这个bean
    public BookDao bookDao(){
        BookDao bookDao1 = new BookDao();
        bookDao1.setLabel("2");
        return bookDao1;
    }



    public Boss boss( @Autowired Car car){
        Boss boss = new Boss(car);
        return boss;
    }
}


/**
 *  总结：
 *
 *  自动装配： 使用DI（依赖注入）完成对容器里面的各个组件的依赖关系赋值
 *
 *  1. @Autowired: 自动注入
 *     1.1 默认按照类型注入，applicationContext.getBean(..class)， 找到容器中相同类型的bean, 然后注入
 *     1.2 如果对应的类型有多个，默认把属性名字当做bean name寻找
 *     1.3 可以在属性上用@Qualifier("bean name") 指定用哪个bean
 *     1.4 可以在bean 上标注@Primary， 标注为@Primary 的bean, 在没有用@Qulifier指定的情况下，优先被注入
 *     1.5 用了@Autowired, 默认必须注入bean, 如果没有找到，就抛错误。
 *         但是可以用 @Autowired(required = false) 这样如果没找到bean, 该属性为null
 *
 *
 *  2. @Resource (JSR250) , @Inject(JSR330)
 *
 *     2.1 两个都为Java规范下的注解，其他框架也能支持 // @Autowired, @Qualifier 为 sprint 下的注解
 *     2.2 @Resource 默认按照属性名字注入，没有支持@Primary 和 @ Autowired(required = false),
 *     2.3 @Inject 需要导入javax的包， 支持@Primary, 不支持 required = false
 *
 *  自动装配原理，利用 BeanPostProcessor:  AutowiredAnnotationBeanPostProcessor
 *
 *
 *  3.@Autowired 可以标注在除了属性之外的：
 *     3.1 方法上，  例如set方法上
 *     3.2 构造器上， 默认使用无参构造器，但是如果有有参构造器， 会使用有参构造器，可以将@Autowired标注在构造器上，在只有一个有参构造器的情况下也可以省略
 *     3.3 参数上, 用@Bean 创造bean的时候可以用， 也可以省略
 *
 *  4. 使用xxxAware能给组件注入Sprint 容器底层的一些组件（ApplicationContext等等）
 *     4.1 bean 实现 xxxAware 接口， 在创建对象的时候会调用相应的方法（beanPostProcessor 中讲过）
 *     4.2 xxxAware： 功能使用xxxProcessor
 *
 */
