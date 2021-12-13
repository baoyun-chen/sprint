package baoyun.lessons.lesson03;

import baoyun.lessons.lesson03.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value="classpath:/lesson03/person.properties")
// 配置文件要放在resources下，classpath 是从resources开始算起
public class MainConfigOfPropertyValues {

    @Bean
    public Person person() {
        return new Person();
    }
}


/**
 *    总结：
 *    1. @PropertySource 加载配置文件， 配置文件放在resources里面，
 *    可以在 applicationContext.getEnvironment().getProperty("nickName") 中拿到
 *
 *    2. 使用@Value给bean的属性赋值
 *      2.1 可以赋值基本类型
 *      2.2 使用#{} 可以赋值SqEL sprint 表达式
 *      2.3 使用${} 可以赋值配置文件中的值
 */

