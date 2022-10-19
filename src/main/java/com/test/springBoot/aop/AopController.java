//package com.test.springBoot.aop;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.Date;
//
///**
// * @Description:
// * @Author: wangyinjia
// * @Date: 2020/4/10
// * @Version: 1.0
// */
//@Aspect
//@Component
//public class AopController {
//    /**
//     * 2 定义切入点，使用切点函数指定com.example包下任意类的任意方法。
//     * 每个通知都支持切点函数，但统一定义pointCut()，可以统一管理切点
//     */
//    @Pointcut("execution(* com.test.springBoot.aop..a*(..))")
//    public void pointCut() {}
//
////    /**
////     * 前置通知
////     */
////    @Before("pointCut()")
////    public void before() {
////        System.out.println("before advice");
////    }
////
////    /**
////     * 后置通知
////     */
////    @After("pointCut()")
////    public void after() {
////        System.out.println("after advice");
////    }
//
//    /**
//     * 返回通知。通过JoinPoint参数获得连接点，通过returning指定返回值参数名，
//     */
//    @AfterReturning(value = "pointCut()", returning = "result")
//    public void afterReturning(JoinPoint joinPoint, Object result) {
//        System.out.printf("afterReturning advice。Method：%s，Args：%s，result：%s\n",
//                joinPoint.getSignature().getName(),
//                Arrays.asList(joinPoint.getArgs()),
//                result);
//    }
//
//    /**
//     * 异常通知。通过JoinPoint参数获得连接点，通过throwing指定异常参数名，
//     */
//    @AfterThrowing(value = "pointCut()", throwing = "e")
//    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
//        System.out.printf("afterThrowing advice。Method：%s，Args：%s，exception：%s\n",
//                joinPoint.getSignature().getName(),
//                Arrays.asList(joinPoint.getArgs()),
//                e);
//    }
//
//    /**
//     * 环绕通知。通过ProceedingJoinPoint参数除了JoinPoint的功能外，还可以控制方法是否执行。
//     */
//    @Around("pointCut()")
//    public Object around(ProceedingJoinPoint point) throws Throwable {
//        System.out.println("before");
//        Date s = new Date();
//        // 该方法有重载，可以修改传入参数
//        Object result = point.proceed();
//        Date e = new Date();
//        System.out.println(e.getTime()-s.getTime());
//        System.out.println("after");
//        return result;
//    }
//
//}
