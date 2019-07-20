package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class MyClass {

    public static void main(String args[]) {

        Schema schema = new Schema(1, "www.rsdznjj.com"); //创建数据库表

        Entity son = schema.addEntity("Son"); //创建实体
        son.addIdProperty();
        son.addStringProperty("name");
        son.addIntProperty("age");
        Property fatherId = son.addLongProperty("fatherId").getProperty(); //外键


        Entity father = schema.addEntity("Father"); //创建实体
        father.addIdProperty();
        father.addStringProperty("name");
        father.addIntProperty("age");

        son.addToOne(father, fatherId); //建立关联
        //son.addToMany(father, fatherId);//建立关联


//        Schema schema=new Schema(1,"www.rsdalstar.com.many");//创建数据库表
//
//        Entity son=schema.addEntity("Son");//创建实体
//        son.addIdProperty();
//        son.addStringProperty("name");
//        son.addIntProperty("age");
//        //Property fatherId=son.addLongProperty("fatherId").getProperty();//外键
//
//
//        Entity father=schema.addEntity("Father");//创建实体
//        father.addIdProperty();
//        father.addStringProperty("name");
//        father.addIntProperty("age");
//        Property sonId=father.addLongProperty("sonId").getProperty();//外键
//
//        father.addToOne(son,sonId);
//        son.addToMany(father,sonId).setName("father");

        try {
            new DaoGenerator().generateAll(schema, "GreenDao/src/main/java");//生成数据库表
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
