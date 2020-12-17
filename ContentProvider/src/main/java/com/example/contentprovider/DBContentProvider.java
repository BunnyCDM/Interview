package com.example.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class DBContentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.example.zsqlite";
    private static final String DB_TABLE = "user";

    private DBOpenHelper mDBOpenHelper;
    private static final UriMatcher sUriMatch = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int USER = 0;
    private static final int USERS = 1;

    static {
        sUriMatch.addURI(AUTHORITY, DB_TABLE, USERS);
        sUriMatch.addURI(AUTHORITY, DB_TABLE + "/#", USER);
    }

    @Override
    public boolean onCreate() {
        mDBOpenHelper = new DBOpenHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = mDBOpenHelper.getReadableDatabase();
        switch (sUriMatch.match(uri)) {
            case USERS:
                return db.query(DB_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
            case USER:
                long id = ContentUris.parseId(uri);
                if (!TextUtils.isEmpty(selection)) {
                    selection = selection + " and id=" + id;
                }
                return db.query(DB_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
            default:
                throw new IllegalArgumentException("query args exception !");
        }
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatch.match(uri)) {
            case USERS:
                return "vnd.android.cursor.dir/user";
            case USER:
                return "vnd.android.cursor.item/user";
        }
        throw new IllegalArgumentException("getType exception !");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
        long id = 0;
        switch (sUriMatch.match(uri)) {
            case USERS:
                id = db.insert(DB_TABLE, null, values);
                return ContentUris.withAppendedId(uri, id);
            case USER:
                id = db.insert(DB_TABLE, null, values);
                String path = uri.toString();
                return Uri.parse(path.substring(0, path.lastIndexOf("/")) + id);
        }
        throw new IllegalArgumentException("insert exception !");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
        int count = 0;
        switch (sUriMatch.match(uri)) {
            case USERS:
                count = db.delete(DB_TABLE, selection, selectionArgs);
                break;
            case USER:
                // 下面的方法用于从URI中解析出id，对这样的路径content://hb.android.teacherProvider/teacher/10
                // 进行解析，返回值为10
                long personid = ContentUris.parseId(uri);
                String where = "id=" + personid;   // 删除指定id的记录
                where += !TextUtils.isEmpty(selection) ? " and (" + selection + ")" : "";   // 把其它条件附加上
                count = db.delete(DB_TABLE, where, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);

        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
        int count = 0;
        switch (sUriMatch.match(uri)) {
            case USERS:
                count = db.update(DB_TABLE, values, selection, selectionArgs);
                break;
            case USER:
                long personid = ContentUris.parseId(uri);
                String where = "id=" + personid;
                where += !TextUtils.isEmpty(selection) ? " and (" + selection + ")" : "";   // 把其它条件附加上
                count = db.update(DB_TABLE, values, where, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);

        }
        return count;
    }

}
