package baoyun.lessons.lesson02;

import baoyun.lessons.lesson02.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 总结：
 *  容器管理Bean的生命周期
 *     Bean的创建 -> Bean的初始化 -> Bean的销毁
 *
 *  Bean的创建/构建：   （constructor)
 *      单实例： 容器创建的时候创建， 只创建一次，之后每次调用的都是同一个
 *      多实例： 调用的时候创建， 每次创建新的
 *
 *  Bean的初始化
 *
 *  Bean的销毁:
 *      单实例： 容器销毁的时候销毁
 *      多实例： 容器不会管理这个bean, 不会销毁这个bean
 *
 *
 *
 * 1. @Bean 里面自定义初始化，销毁方法  @Bean(init-method="", destroy-method="")
 * 2. 通过实现 InitializingBean（初始化逻辑） 和 DisposableBean （销毁逻辑） 两个接口里面的方法 afterPropertiesSet, destroy
 * 3. 在bean class 里面的方法里使用  @PostConstruct 和 @PreDestroy 注解
 *      3.1 @PostConstruct 在bean 创建完成并且赋值完成后调用带有此注解的方法
 *      3.2 @PreDestroy 在bean 销毁之前调用带有此注解的方法
 * 4. 自定义BeanPostProcessor(后置处理器） 实现 BeanPostProcessor 接口
 *      4.1 postProcessBeforeInitialization 在创建完成，初始化之前调用此方法
 *      4.2 postProcessAfterInitialization 在创建完成，初始化之后调用此方法
 *      4.3 将BeanPostProcessor 注入到容器中后，容器中所以的bean都会使用这些方法
 *
 *
 *
 * BeanPostProcessor 原理：
 *
 *  doCreateBean{
 *      populateBean() --> 给bean的属性赋值
 *      initializeBean{   --> 初始化bean
 *          applyBeanPostProcessorsBeforeInitialization()  --> 拿到所有的后置处理器，逐个执行beforeInitialization, 一旦返回null,跳出，不执行后面的
 *          invokeInitMethods()
 *          {
 *              afterPropertiesSet()
 *              invokeCustomInitMethod()  --> 使用自定义的初始化方法
 *          }
 *          applyBeanPostProcessorsAfterInitialization()
 *      }
 *  }
 *
 *
 *   Sprint 底层对BeanPostProcessor 的使用：
 *      bean赋值，注入其他组件， @Autowired 等等都是使用了BeanPostProcessor
 *
 */

@Configuration
@ComponentScan("baoyun.lessons.lesson02")
public class MainConfigOfLifeCycle {

    @Bean(value = "car01", initMethod = "init", destroyMethod = "destroy")
    public Car car01(){
        return new Car();
    }

}
