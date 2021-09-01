package com.kl.aop;

import android.content.Context;
import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PermissonAspect {



    //定义入口
    //注解 访问权限 返回值类型 类名 函数名（参数）
    @Pointcut("execution(@com.kl.aop.PermissionNeed * *(..)) && @annotation(permissionNeed)")
    public void requestPermission(PermissionNeed permissionNeed){
        Log.d("bunny", "requestPermission: ");
    }


    @Around("requestPermission(permissionNeed)")
//    @Around("execution(@com.kl.aop.PermissionNeed * *(..)) && @annotation(permissionNeed)")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint,PermissionNeed permissionNeed){
//        //实现功能
//        Object obj = joinPoint.getThis();
//        //若不是通过activity不能强转，需要通过反射获取上下文
//        Context context= (Context) obj;
//        //启动创建activity，透明的
        Log.d("bunny", "aroundJoinPoint: ");
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }




}
