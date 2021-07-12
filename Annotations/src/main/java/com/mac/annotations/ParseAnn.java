package com.mac.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ParseAnn {


    public static void main(String[] args) {


        System.out.println("=======start======");

        try {
            //1、使用类加载器加载类
            Class c=Class.forName("com.mac.annotations.Child");
            //2、找到类上面的注解 "
            boolean isExist=c.isAnnotationPresent(Description.class);
            //3、拿到注解实例
            if(isExist){
                Description description= (Description) c.getAnnotation(Description.class);
                System.out.println(description.value());
            }
            //4、找到方法上的注解
            Method[] ms=c.getMethods();
            for (Method m:ms) {
                boolean isMExist=m.isAnnotationPresent(Description.class);
                if(isMExist){
                    Description d= (Description) m.getAnnotation(Description.class);
                    System.out.println(d.value());
                }
            }


            System.out.println("=======另外一种解析方法======");
            //另外一种解析方法
            for (Method m:ms) {
                Annotation[] as=m.getAnnotations();
                for (Annotation a:as) {
                    if(a instanceof Description){
                        Description d= (Description) a;
                        System.out.println(d.value());
                    }
                }

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
