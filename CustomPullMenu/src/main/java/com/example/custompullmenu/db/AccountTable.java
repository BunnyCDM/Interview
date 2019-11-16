package com.example.custompullmenu.db;

/**
 * Created by mac on 2019-11-16.
 */
public class AccountTable {

    /**
     * 表
     */
    public final static String TABLE_NAME = "account";

    /**
     * 字段
     */
    public static final String ID = "id";
    public static final String PHONE = "phone";
    public static final String NAME = "name";
    public static final String TIME = "time";
    public static final String FULLNAME = "fullName";

    /**
     * 字段ID 数据库操作建立字段对应关系 从0开始
     */
    public static final int ID_ID = 0;
    public static final int ID_PHONE = 1;
    public static final int ID_NAME = 2;
    public static final int ID_TIME = 3;
    public static final int ID_FULLNAME = 3;

    /**
     * 创建表
     */
    public final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PHONE + " VARCHAR(20), " +
            NAME + " VARCHAR(20), " +
            TIME + " INTEGER(100), " +
            FULLNAME + " VARCHAR(20))";

//    public static final String CREATE_TABLE = "create table if not exists " + TABLE_NAME + "(" +
//            ID + " text primary key, " +
//            NAME + " text, " +
//            IS_ENABLE + " text default '1', " +
//            POSITION + " text) ";

}
