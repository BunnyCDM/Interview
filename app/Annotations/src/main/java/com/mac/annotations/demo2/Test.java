package com.mac.annotations.demo2;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {


    public static void main(String[] args) {
        Filter f1 = new Filter();
        f1.setId(10);


        Filter f2 = new Filter();
        f2.setUserName("bunny");


        Filter f3 = new Filter();
        f3.setEmail("15007190899@163.com");

        String sq1 = query(f1);
//        String sq2 = query(f2);
//        String sq3 = query(f3);
        System.out.println(sq1);
//        System.out.println(sq2);
//        System.out.println(sq3);

    }

    private static String query(Filter f) {
        StringBuilder sb = new StringBuilder();

        //1、获取到class
        Class c = f.getClass();

        //2、获取到table的名字
        boolean exists = c.isAnnotationPresent(Table.class);
        if (!exists) {
            return null;
        }

        Table t = (Table) c.getAnnotation(Table.class);
        String tableName=t.value();
        sb.append("select * from ").append(tableName).append("where 1=1");

        //3、遍历所有的字段
        Field[] fArray = c.getDeclaredFields();
        for (Field field:fArray) {
            //4、处理每个字段对应的sql
            //41、拿到字段名
            boolean fExists=field.isAnnotationPresent(Column.class);
            if(!fExists){
                continue;
            }
            Column column=field.getAnnotation(Column.class);
            String columnName=column.value();
            //42、拿到字段值
            String filedName=field.getName();
            String getMethodName="get"+filedName.substring(0,1).toUpperCase()+filedName.substring(1);
            Object fieldValue=null;
            try {
                Method getMethod=c.getMethod(getMethodName);
                try {
                     fieldValue= (String) getMethod.invoke(f);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            //43、拼装sql
            sb.append(" and ").append(filedName).append("=").append(fieldValue);

        }

        return sb.toString();
    }

}
