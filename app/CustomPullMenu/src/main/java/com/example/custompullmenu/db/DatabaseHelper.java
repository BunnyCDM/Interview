package com.example.custompullmenu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.custompullmenu.CustompullmenuApplication;

/**
 * Created by louliang on 2018/05/05.
 * <p>
 * 使用详情：https://www.jb51.net/article/135610.htm
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "hayden.db";
    private static final int DB_VERSION = 1;
    private static DatabaseHelper instance = null;
    private static SQLiteDatabase db = null;

    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private static synchronized DatabaseHelper getInstance() {
        if (instance == null) {
            instance = new DatabaseHelper(CustompullmenuApplication.AppContext);
        }
        return instance;
    }

    public static synchronized SQLiteDatabase getSQLiteDatabase() {
        if (db == null) {
            db = getInstance().getWritableDatabase();
        }
        return db;
    }

    public static synchronized void closeSQLiteDatabase() {
        if (db != null) {
            db.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("CREATE TABLE account(_id INTEGER PRIMARY KEY AUTOINCREMENT,phone VARCHAR(20),name VARCHAR(20),time INTEGER(100),fullName VARCHAR(20))");
        db.execSQL(AccountTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: 2019-11-16 特别要注意的哈！！
        switch (oldVersion) {

            case 1:
                break;

            case 2:
                break;

            default:
                break;

        }

    }
}
