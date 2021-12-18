package baoyun.lessons.lesson05.beans;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class Log {

    // 抽取公共切入点表达式
    // 权限修饰符+返回类型+方法名
    // ("public int baoyun.lessons.lesson05.beans.MathCalculator.*(..)")  * 代表所有方法 （..)任意参数
    @Pointcut("execution(int baoyun.lessons.lesson05.beans.MathCalculator.div(..))")
    public void pointCut(){ }

    // 括号里面的为切入点表达式

    @Before("pointCut()")
    public void before(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        System.out.println("Before "+methodName +"....");
        Arrays.stream(args).forEach(System.out::println);
    }


    @After("pointCut()")   // 本类引用
    public void after(){
        System.out.println("After...");
    }

    // 用returning 指定获取结果的variable的名字
    @AfterReturning(value="pointCut()", returning = "result")  // 外部类引用
    public void afterReturning(JoinPoint joinPoint, Object result){  // JoinPoint joinPoint 必须是第一个参数
        System.out.println("After Returning...:" + result);
    }

    // throwing 指定获取异常的variable的名字
    @AfterThrowing(value = "pointCut()", throwing="throwing")
    public void afterThrowing(Exception throwing){
        System.out.println("After Throwing...: " + throwing);
    }


    @Pointcut("execution(int baoyun.lessons.lesson05.beans.MathCalculator.multiple(..))")
    public void pointCut2(){}


    //ProceedingJoinPoint  是 JoinPoint 的子接口，只用于@Around中
    // Object proceed throw Throwable // 执行目标方法
    // Ojbect proceed(Object[] var1)  throw Throwable  传入新的参数执行目标方法
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
