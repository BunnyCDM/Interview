package com.kl.aop.reflection;

import android.app.Application;

import java.lang.reflect.InvocationTargetException;

public class ApplicationUtil {

    private static Application application;

    public static Application getApplication() {
        if (application != null) {
            return application;
        } else {
            application = getApplicationByReflect();
        }
        return application;
    }

    private static Application getApplicationByReflect() {
        try {
            Class<?> clazz = Class.forName("android.app.ActivityThread");
            try {
                Object currentActivityThread = clazz.getMethod("currentActivityThread").invoke(null);
                Object application = clazz.getMethod("getApplication").invoke(currentActivityThread);
                return (Application) application;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("application 为空");
    }

}
