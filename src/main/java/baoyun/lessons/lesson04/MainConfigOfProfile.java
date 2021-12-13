package baoyun.lessons.lesson04;


import baoyun.lessons.lesson04.beans.Car;
import baoyun.lessons.lesson04.beans.DataBaseConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

//@Profile("test")  // 当前激活环境没有test的时候，整个配置类都不会被加载，所以里面被标注为其他环境里面激活的bean/没有标注激活环境的bean,也不会被加载
@Configuration
@PropertySource(value = "classpath:/lesson04/config.properties")
public class MainConfigOfProfile implements EmbeddedValueResolverAware {

    @Value("${db_user}")  // 可以用在属性上
    private String user;
    private String password;
    private String address;

    private StringValueResolver valueResolver;

    @Bean(value = "Car")  // 没有标注@Profile的话，任何环境都会被加载
    public Car getCar(){
        return new Car();
    }

    @Profile(value= "dev")    // VM option： -Dspring.profiles.active=dev
    @Bean(value = "DataBaseConfigDev")
    public DataBaseConfig getDataBaseConfigDev(@Value("${db_password}")String password){ //可以用在方法参数上
       DataBaseConfig dataBaseConfig = new DataBaseConfig();
       dataBaseConfig.setUser(user);
       dataBaseConfig.setPassword(password);
       dataBaseConfig.setAddress(address);
       return dataBaseConfig;
   }

    @Profile(value= "default")    // VM option： -Dspring.profiles.active=dev
    @Bean(value = "DataBaseConfigStg")
    public DataBaseConfig getDataBaseConfigStg(@Value("${db_password}")String password){ //可以用在方法参数上
        DataBaseConfig dataBaseConfig = new DataBaseConfig();
        dataBaseConfig.setUser(user);
        dataBaseConfig.setPassword(password);
        dataBaseConfig.setAddress(address);
        return dataBaseConfig;
    }

    @Profile(value= "test")
    @Bean(value = "DataBaseConfigTest")
    public DataBaseConfig getDataBaseConfigTest(@Value("${db_password}")String password){
        DataBaseConfig dataBaseConfig = new DataBaseConfig();
        dataBaseConfig.setUser(user);
        dataBaseConfig.setPassword(password);
        dataBaseConfig.setAddress(address);
        return dataBaseConfig;
    }

    @Profile(value= "prd")
    @Bean(value = "DataBaseConfigPrd")
    public DataBaseConfig getDataBaseConfigPrd(@Value("${db_password}")String password){
        DataBaseConfig dataBaseConfig = new DataBaseConfig();
        dataBaseConfig.setUser(user);
        dataBaseConfig.setPassword(password);
        dataBaseConfig.setAddress(address);
        return dataBaseConfig;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.valueResolver = resolver;
        this.address = valueResolver.resolveStringValue("${db_address}");  // 用后置处理器拿到配置
    }
}

/**
 *  总结：
 *  1. 使用@Profile注解能根据环境不同，动态加载不同的bean
 *      1.1 标注了@Profile(env)的bean, 只在该环境被激活的时候才会加载
 *      1.2 没有标注的bean,所以环境中都会被加载
 *      1.3 设置激活的环境有两种方法：
 *          1.3.1. 使用VM Option, 加上-Dspring.profiles.active=dev,test
 *          1.3.2  使用代码（见test03）
 *      1.4 没有设置激活环境，默认激活default环境
 *      1.5 将@Profile加到配置类里面，如果环境没有被激活，整个配置类都不会被加载
 */
