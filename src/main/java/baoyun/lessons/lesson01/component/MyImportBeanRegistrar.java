package baoyun.lessons.lesson01.component;

import baoyun.beanClass.color.Red;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;


/**
 * 手动注册bean
 */
public class MyImportBeanRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * BeanDefinitionRegistry:  注册类
     *          BeanDefinition: 每一个bean有自己的一个BeanDefinition
     *          调用 registerBeanDefinition 方法， 向容器里注册一个bean
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        if(registry.containsBeanDefinition("baoyun.beanClass.color.Red")){
            // 创建了一个BeanDefinition, bean定义信息包括bean的类等等
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Red.class);
            // 用这个bean的定义信息给容器注册一个id为registerBean的bean
            registry.registerBeanDefinition("registerBean", beanDefinition);
        }
    }

}
