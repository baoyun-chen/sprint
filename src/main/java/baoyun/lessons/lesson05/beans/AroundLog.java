package baoyun.lessons.lesson05.beans;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AroundLog {

    @Pointcut("execution(int baoyun.lessons.lesson05.beans.MathCalculator.multiple(..))")
    public void pointCut2(){

    }
    @Around("pointCut2()")
    public Object around(ProceedingJoinPoint joinPoint){
        Object result = null;
        try{
            System.out.println("Around Before...");
            result = joinPoint.proceed();
            System.out.println("Around after returning...");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("Around after throwing...");
        }
        System.out.println("Around after...");
        return result;

    }
}
