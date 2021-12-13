package baoyun.lessons.lesson01;

import baoyun.beanClass.color.Color;
import baoyun.beanClass.animals.Cat;
import baoyun.lessons.lesson01.component.*;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScans(
        value= {

                @ComponentScan(value = "baoyun",
                        includeFilters =  {
                        @ComponentScan.Filter(type= FilterType.CUSTOM, classes = {MyTypeFilter.class})
                },useDefaultFilters = false),


})
//excludeFilters: exclude some classes ( not working with custom???
//includeFilters: only scan those classes
@Import({Color.class, MyImportSelector.class, MyImportBeanRegistrar.class})   //导入组件，id 默认是全类名

public class MainConfig {
    /*
     *
     *  @Scope("singleton")
     *
     * //prototype  获取bean的时候创建, 每次获取都会新建
     * //singleton  默认值  ioc 容器启动的时候创建bean  --> 懒加载：第一次使用的时候再创建并初始化   @Lazy
     *
     *
     */
    @Bean("cat")
    public Cat cat(){
        return new Cat();
    }


    /*
        @Conditional, 满足条件的bean才会被加载
        可以加到方法或者类上
        值是一个 element为extend了Condition的类 的数组
     */
    @Conditional({Condition01.class})
    @Bean("cat01")
    public Cat cat1(){
        return new Cat("cat01");
    }

    @Bean("cat02")
    public Cat cat2(){
        return new Cat("cat02");
    }

    @Bean("factoryBean")
    public MyFactoryBean factoryBean(){
        return new MyFactoryBean();
    }

}

/**
 * 总结： 给容器中注册组件的方法
 * 1. 用扫描+组件标注注解  （ @ComponentScans/@ComponentScan + @Component, @Service, @Repository, @Controller)
 *      1.1 @ComponentScan 能扫描指定路径：value = "..."，
 *      1.2 @ComponentScan 能与includeFilters/excludeFilter 一起作用 包括/排除某些类
 *      1.3 @ComponentScan.Filter 能自定义filter, 自定义filter类 implement TypeFilter接口
 *      1.4 @ComponentScans 定义一组扫描规则
 *
 *      这种方法常用于注入自己创建的类
 *
 * 2. 用@Bean 导入第三方包里面的组件： 在config class里面创建实例，用@bean注入
 *      2.1 能与@Conditional一起使用，实现条件注入
 *      2.2 自定义的condition class implement Condition接口
 *
 * 3. 用@Import 快速给容器中导入一个组件
 *      3.1 直接用类的导入， 容器中会自动注册这个组件，id 默认是全类名
 *      3.2 用ImportSelector, 自定义ImportSelector implement 自定义ImportSelector接口， 返回要导入的类的全类名数组
 *      3.3 用ImportBeanDefinitionRegistrar: 手工注册bean到容器里面
 *
 * 4. 用工厂bean(FactoryBean)
 *      4.1 自定义FactoryBean implement FactoryBean接口
 *      4.2 通过FactoryBean获取bean, 默认获取的是 getObject 返回的对象
 *      4.3 通过加"&"到id前面，能获取到工厂bean本身
 *
 */
