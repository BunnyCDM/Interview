package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by mac on 2019/2/28.
 */

public class MyPerson {

    public static void main(String args[]) {


        Schema schema = new Schema(1, "www.rsdznjj.com.person");//创建数据库表

        Entity person = schema.addEntity("Person");//创建实体
        person.addIdProperty();
        person.addStringProperty("_id");
        person.addStringProperty("name");
        person.addIntProperty("age");

        try {
            new DaoGenerator().generateAll(schema, "GreenDao/src/main/java");//生成数据库表
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
