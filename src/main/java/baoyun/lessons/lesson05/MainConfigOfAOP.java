package baoyun.lessons.lesson05;


import baoyun.lessons.lesson05.beans.AroundLog;
import baoyun.lessons.lesson05.beans.Log;
import baoyun.lessons.lesson05.beans.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {

    @Bean
    public MathCalculator mathCalculator(){
        return new MathCalculator();
    }


    @Bean
    public Log log(){
        return new Log();
    }

   /* @Bean
    public AroundLog aroundlog(){
        return new AroundLog();
    }*/
}
;

/**
 *    AOP: 在代码运行过程中，动态把某段代码切入到指定方法的指定位置进行运行的编程方式
 *     1. 需要导入AOP组件： spring-aspects
 *     2. 定义一个业务逻辑类
 *     3. 定义一个切面类， 切面类里面定义的方法，是要在指定时间点被切到指定位置执行的方法（advice）
 *        3.1 通知方法有以下的类别：
 *              前置通知： @Before, 在目标方法运行之前运行的
 *              后置通知： @After, 在目标方法运行之后运行的
 *              返回通知： @AfterReturning, 在目标方法正常返回之后运行
 *              异常通知： @AfterThrowing, 在目标方法抛出异常之后运行
 *              环绕通知： @Around, 动态在之前之后，返回之后，异常之后运行的, 需要手动推动
 *         3.2 切入点： advice被运行的目标方法（ 业务逻辑里面的某个方法）
 *         3.3 JoinPoint: 这个类把切入点的信息组装了, 可以用这个获得目标方法的数据，但是只能用于第一个参数
 *         3.4 ProceedingJoinPoint: 只能用于Around, ProceedingJoinPoint.proceed() 运行目标方法，手动在之前或之后添加切入的代码
 *     4. 给切面类的方法标注何时何地运行 （切入点表达？？？）
 *          4.1 @Pointcut (???)
 *              建一个void 的方法，例如 public void pointcutmethod(){}， 不用写实际代码，标注@Pointcut("execution(切入点表达式)")，
 *              在@Before，@After 等标注中使用, eg @Before("pointcutmethod()")
 *     5. 将业务逻辑类和切面类都要加入到容器中，并且一定要用容器中的逻辑类组件，自己定义的AOP不会运行
 *     6. 用@Aspect在类上标注哪个是切面类
 *     7. 用@EnableAspectJAutoProxy 开启AOP的开关
 *
 *
 *      @EnableAspectJAutoProxy是什么：
 *          @Import(AspectJAutoProxyRegistrar.class)
 *          利用AspectAutoProxyRegistrar 给容器注册了一个叫为internalAutoProxyCreator的bean的定义
 *          这个bean的类型是 AnnotationAwareAspectJAutoProxyCreator  （还没有创建对象）
 *
 *          AnnotationAwareAspectJAutoProxyCreator -> AspectJAwareAdvisorAutoProxyCreator
 *            -> AbstractAdvisorAutoProxyCreator -> AbstractAutoProxyCreator
 *            implement SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *            后置处理器，自动装配BeanFactory
 *          ----------------------------------------------------
 *            AbstractAutoProxyCreator.setBeanFactory()
 *            AbstractAutoProxyCreator 有后置处理器的逻辑
 *
 *            AbstractAdvisorAutoProxyCreator.setBeanFactory() -> initBeanFactory()
 *            AnnotationAwareAspectJAutoProxyCreator -> initBeanFactory()
 *          ----------------------------------------------------
 *
 *          流程：
 *          1. 传入配置类，创建一个IoC容器
 *          2. 注册配置类，调用refresh, 刷新启动容器
 *          3. registerBeanPostProcessors(beanFactory) 注册BeanPostProcessors来拦截bean的创建
 *              3.1 根据类型拿到已经定义了但还没有创建对象的BeanPostProcessor   // 每个bean 都是先定义了再创建对象再注入容器
 *                  registerBeanPostProcessors {
 *                      beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);
 *                  }
 *                  3.1.1 @EnableAspectJAutoProxy 利用AspectAutoProxyRegistrar
 *                      给容器注册了一个类型为AnnotationAwareAspectJAutoProxyCreator 名字为internalAutoProxyCreator的bean的定义
 *                      // 此时还没有创建对象
 *              3.2 给容器中加别的BeanPostProcessor
 *              3.3 注册实现了PriorityOrdered接口的BeanPostProcessor
 *              3.4 再给容器中注册实现了Ordered接口的BeanPostProcessor
 *              3.5 再注册没有实现优先级接口的BeanPostProcessor
 *              3.6 注册BeanPostProcessor，实际上就是创建BeanPostProcessor对象，保存在容器中
 *                  创建internalAutoProxyCreator[AnnotationAwareAspectJAutoProxyCreator]
 *                      3.6.1  如果是单实例，先从缓存里面看看有没有已经创建好了的，没有再创建实例
 *                             AbstractAutowireCapableBeanFactory#doCreateBean(java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Object[])
 *                      3.6.2  创建完之后 populateBean， 给bean的属性赋值
 *                      3.6.3  初始化 bean initializeBean()  --> 后置处理器工作
 *                             3.6.3.1 invokeAwareMethods()  判断bean 是不是Aware接口，如果是处理Aware接口回调
 *                             3.6.3.2 applyBeanPostProcessorsBeforeInitialization() 应用后置处理器的BeforeInitialization的方法
 *                             3.6.3.3 invokeInitMethods() 执行初始化方法
 *                             3.6.3.4 applyBeanPostProcessorsAfterInitialization  应用后置处理器的AfterInitialization的方法
 *                       3.6.4 AnnotationAwareAspectJAutoProxyCreator 创建成功
 *               3.7 把BeanPostProcessor注册到BeanFactory里面
 *                      beanFactory.addBeanPostProcessor()
 *
 *  ======================================== 以上是创建和注册 AnnotationAwareAspectJAutoProxyCreator 的过程
 *
 *
 *          4. finishBeanFactoryInitialization  完成beanFactory初始化工作
 *              4.1 遍历获取容器中所有的bean, 依次创建对象getBean()->doGetBean()->getSingleton()->
 *              4.2 先从缓存里面看看有没有已经创建好了的，没有再创建实例 createBean()
 *                  4.2.1 resolveBeforeInstantiation(beanName, mbdToUse); 解析BeforeInstantiation
 *                  希望后置处理器在此能返回一个代理对象，如果能返回代理对象就返回，
 *                      4.2.1.1 实例化后置处理器先尝试返回对象
 *                          bena = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName)
 *                          拿到所有后置处理器，如果是InstantiationAwareBeanPostProcessor，就执行这个后置处理器里面的postProcessBeforeInstantiation方法
 *                          if(bean!=null){
 *                              bean = this.applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *                          }
 *
 *                        //AnnotationAwareAspectJAutoProxyCreator 是一个 InstantiationAwareBeanPostProcessor 后置处理器，
 *                        //其他所有的bean 创建的之前都会被拦截，用它里面的postProcessBeforeInstantiation方法尝试创建代理对象
 *
 *                  4.2.2 如果不能用doCreateBean创建（3.6流程）这里才是正在的创建bean实例
 *
 *  AnnotationAwareAspectJAutoProxyCreator【InstantiationAwareBeanPostProcessor】 的作用：
 *      里面有
 *      1. postProcessBeforeInstantiation
 *      2. afterProcessBeforeInstantiation
 *      3. postProcessAfterInitialization
 *
 *
 *  1. 每一个bean创建之前，调用里面的postProcessBeforeInstantiation  --> 4.2.1.1
 *      1.1 MathCalculator的创建：
 *          1.1.1 判断当前bean是否在advisedBeans中   // advisedBeans保存了所有需要增强的bean
 *              // 需要增强的bean的意思是，有被切面切到，在某些方法前切入其他代码的bean, 例如 MathCalculator
 *          1.1.2 判断当前bean是否是基础类型的 Advice, Pointcut, AopInfrastructureBean
 *          1.1.3 判断是否需要跳过
 *              1.1.3.1 拿到所有的Advice(增强器） 【标注了@Before, @AfterReturning....的方法】放到 List<Advisor> candidateAdvisors
 *               判断每个增强器是不是AspectJPointcutAdvisor, 目前的增强器都是InstantiationModelAwarePointcutAdvisor: 返回false
 *
 *  2. 创建对象之后, 调用里面的 postProcessAfterInitialization
 *      2.1 MathCalculator 创建之后：
 *          this.wrapIfNecessary(bean, beanName, cacheKey)  //在需要的情况下包装
 *          2.1.1 获取当前bean的所有增强器（通知方法） Object[] specificInterceptors
 *              找到能在当前bean使用的增强器（找哪些通知方法是需要切入当前bean方法的）
 *              给增强器排序，按照用的先后顺序
 *          2.1.2 保存当前bean 在增强bean缓存里
 *          2.1.3 如果当前bean需要增强，创建当前bean的代理对象
 *              2.1.3.1 获取所有增强器
 *              2.1.3.2 保存到proxyFactory中
 *              2.1.3.3 创建代理对象：Spring自动决定
 *                  JdkDynamicAopProxy  还是 ObjenesisCglibAopProxy
 *          2.1.4 给容器中返回当前组件使用Cglib增强了的对象
 *          所以当我们去容器中的MathCalculator来执行方法，实际上是使用了 Cglib增强了的MathCalculator的代理对象
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
