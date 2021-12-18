package baoyun.config.test;

import baoyun.lessons.lesson05.MainConfigOfAOP;
import baoyun.lessons.lesson05.beans.MathCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class lesson05Test {


    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        try{

            MathCalculator mathCalculator = (MathCalculator)applicationContext.getBean("mathCalculator");

            mathCalculator.div(1,1);

            mathCalculator.multiple(1,2);

            mathCalculator.div(1,0);


        }catch (Exception e){

        }finally {
            applicationContext.close();
        }


    }
}
