package com.kl.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class LogAspect {

    @Pointcut("within(com.kl.aop.*..*)")
    public void withinApp() {
    }

    @Pointcut("!within(com.kl.aop.AppLogger)")
    public void withoutLog() {
    }

    @Pointcut(value = "call(static * android.util.Log.*(..)) && !within(LogAspect)")
    public void logCall() {
    }

    @Pointcut("logCall() && withinApp() && withoutLog()")
    public void logInApp() {
    }

    @Around("logInApp()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String kind = joinPoint.getKind();
        if (ProceedingJoinPoint.METHOD_CALL.equals(kind)) {
            logAround(joinPoint.getArgs(), joinPoint.getSignature().getName().toUpperCase());
            return null;
        } else {
            return joinPoint.proceed();
        }
    }

    private void logAround(Object[] args, String methodName) {
        if (args != null && args.length >= 2) {
            if (args.length == 2) {
                log4TwoP(args, methodName);
            } else if (args.length == 3) {
                log4ThreeP(args, methodName);
            } else {
                log4TwoP(Arrays.copyOf(args, 2), methodName);
            }
        } else {
            AppLogger.w("log 不支持只传递一个参数的调用");
        }
    }

    private void log4TwoP(Object[] args, String methodName) {
        switch (methodName) {
            case "I":
                AppLogger.i(String.valueOf(args[0]), String.valueOf(args[1]));
                break;
            case "D":
                AppLogger.d(String.valueOf(args[0]), String.valueOf(args[1]));
                break;
            case "W":
                AppLogger.w(String.valueOf(args[0]), String.valueOf(args[1]));
                break;
            case "E":
                AppLogger.e(String.valueOf(args[0]), String.valueOf(args[1]));
                if (args[1] instanceof Throwable) {
                    AppLogger.e(String.valueOf(args[0]), ((Throwable) args[1]));
                }
                break;
            case "V":
            default:
                AppLogger.v(String.valueOf(args[0]), String.valueOf(args[1]));

        }
    }

    private void log4ThreeP(Object[] args, String methodName) {
        switch (methodName) {
            case "I":
                AppLogger.i(String.valueOf(args[0]), String.valueOf(args[1]));
                break;
            case "D":
                AppLogger.d(String.valueOf(args[0]), String.valueOf(args[1]));
                break;
            case "W":
                AppLogger.w(String.valueOf(args[0]), String.valueOf(args[1]));
                break;
            case "E":
                AppLogger.e(String.valueOf(args[0]), String.valueOf(args[1]));
                if (args[2] instanceof Throwable) {
                    AppLogger.e(String.valueOf(args[0]), String.valueOf(args[1]), ((Throwable) args[2]));
                }
                break;
            case "V":
            default:
                AppLogger.v(String.valueOf(args[0]), String.valueOf(args[1]));
        }
    }

}
