package com.example.custompullmenu.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录历史增删改查
 */

public class AccountDao {

    private SQLiteDatabase db;

    public AccountDao() {
        this.db = DatabaseHelper.getSQLiteDatabase();
    }

    public void insert(AccountBean info) {
        //根据手机号判断去重
        String[] colum = {"phone"};
        String where = "phone" + "= ?";
        String[] whereValue = {info.getPhone()};
        Cursor cursor = db.query(AccountTable.TABLE_NAME, colum, where, whereValue, null, null, null);
        String phone = null;
        while (cursor.moveToNext()) {
            phone = cursor.getString(cursor.getColumnIndex("phone"));
        }
        cursor.close();
        ContentValues values = new ContentValues();
        values.put(AccountTable.PHONE, info.getPhone());
        values.put(AccountTable.NAME, info.getName());
        values.put(AccountTable.TIME, info.getTime());
        if (!TextUtils.isEmpty(phone)) {
            db.update(AccountTable.TABLE_NAME, values, "phone" + "=?", new String[]{phone});
        } else {
            db.insert(AccountTable.TABLE_NAME, null, values);
        }
        //db.close();
    }

    public int delete(String phone) {
        int count = db.delete(AccountTable.TABLE_NAME, "phone=?", new String[]{phone + ""});
        //db.close();
        return count;
    }

    public List<AccountBean> queryAll() {
        Cursor cursor = db.query(AccountTable.TABLE_NAME, null, null, null, null, null, null);
        List<AccountBean> list = new ArrayList();
        while (cursor.moveToNext()) {
            AccountBean accountBean = new AccountBean();
            // TODO: 2019-11-16 方式一
//            accountBean.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
//            accountBean.setName(cursor.getString(cursor.getColumnIndex("name")));
//            accountBean.setTime(cursor.getLong(cursor.getColumnIndex("time")));
            // TODO: 2019-11-16 方式二
            accountBean.setPhone(cursor.getString(AccountTable.ID_PHONE));
            accountBean.setName(cursor.getString(AccountTable.ID_NAME));
            accountBean.setTime(cursor.getLong(AccountTable.ID_TIME));
            list.add(accountBean);
        }

        // TODO: 2019-11-16 关闭数据库需要你注意哈 
        //db.close();
        //DatabaseHelper.closeSQLiteDatabase();
        cursor.close();
        return list;
    }
}
