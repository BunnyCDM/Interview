package com.example.contentprovider;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
	private DBOpenHelper mDBOpenHelper;
	
	public DBManager(Context context) {
		this.mDBOpenHelper = new DBOpenHelper(context);
	}

	public void add(User user) {
		SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
		db.execSQL("insert into user(username,password) values(?,?)", new Object[]{user.getUsername(), user.getPassword()});
	}
	
	public void deleteWithId(String id){
		SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
		db.execSQL("delete from user where id=?", new Object[]{id});
	}
	
	public void deleteWithUserName(String name){
		SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
		db.execSQL("delete from user where username=?", new Object[]{name});
	}
	
	public void update(User user){
		SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
		db.execSQL("update user set username=? ,password=? where id=?", new Object[]{user.getUsername(), user.getPassword(), user.getId()});
	}
	
	public List<User> getAllUserList(){
		List<User> list = new ArrayList<User>();
		SQLiteDatabase db = mDBOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from user", null);
		while(cursor.moveToNext()){
			String id = cursor.getString(cursor.getColumnIndex("id"));
			String username = cursor.getString(cursor.getColumnIndex("username"));
			String password = cursor.getString(cursor.getColumnIndex("password"));
			User user = new User(id, username, password);
			list.add(user);
		}
		
		return list;
	}
}
