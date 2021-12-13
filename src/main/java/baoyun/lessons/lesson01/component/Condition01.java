package baoyun.lessons.lesson01.component;


import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class Condition01 implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConfigurableBeanFactory beanFactory = context.getBeanFactory();
        Environment env = context.getEnvironment();
        String osName = env.getProperty("os.name");
        System.out.println(osName);    // -Dos.name   可以在vm.option 里面更改虚拟机的设置
        return osName.contains("Mac");
    }
}
