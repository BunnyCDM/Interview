package com.example.butterknife;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class InjectManager {

    public static void inject(Object obj) {
        injectLayout(obj);
        injectView(obj);
        injectOnClick(obj);
    }


    private static void injectOnClick(Object obj) {
        //事件注入
        //setOnClickListener()
        Class<?> aClass = obj.getClass();

        Method[] declaredMethods = aClass.getDeclaredMethods();

        for (Method method : declaredMethods) {
            OnClick annotation = method.getAnnotation(OnClick.class);
            if (annotation != null) {

                if (obj instanceof Activity) {

                    if (annotation.value() == -1) return;

                    try {
                        View view = ((Activity) obj).findViewById(annotation.value());//通过注解的值获取View控件
                        if (view == null) return;

                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    method.invoke(obj);//通过反射来调用被注解修饰的方法
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        }
    }


    private static void injectView(Object obj) {
        //控件注入
        //findViewById()
        Class<?> aClass = obj.getClass();

        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            InjectView annotation = field.getAnnotation(InjectView.class);
            if (annotation != null) {
                try {
                    Method findViewById = aClass.getMethod("findViewById", int.class);


                    int value = annotation.value();
                    View invoke = (View) findViewById.invoke(obj, value);

                    field.setAccessible(true);
                    field.set(obj, invoke);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static void injectLayout(Object obj) {
        //布局注入
        //setContentView(R.layout.activity_main);
        //通过反射获取

        Class<?> aClass = obj.getClass();

        InjectLayout annotation = aClass.getAnnotation(InjectLayout.class);

        if (annotation != null) {

            try {
                Method setContentView = aClass.getMethod("setContentView", int.class);
                //静态、非静态方法

                int value = annotation.value();
                setContentView.invoke(obj, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
