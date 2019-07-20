package com.example.greendao;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.Map;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.WhereCondition;
import www.rsdznjj.com.DaoMaster;
import www.rsdznjj.com.DaoSession;
import www.rsdznjj.com.Father;
import www.rsdznjj.com.FatherDao;
import www.rsdznjj.com.Son;
import www.rsdznjj.com.SonDao;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();

    private DaoMaster.DevOpenHelper helper;

    private SQLiteDatabase db;

    private DaoMaster master;
    private DaoSession session;
    private FatherDao fatherDao;
    private SonDao sonDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openDb();
    }

    private void openDb() {

        //方式一
//        helper=new DaoMaster.DevOpenHelper(MainActivity.this,"person.db",null);
//        db=helper.getWritableDatabase();
//        master=new DaoMaster(db);

        //方式二
        db=new DaoMaster.DevOpenHelper(MainActivity.this,"person.db",null).getWritableDatabase();
        master=new DaoMaster(db);

        session = master.newSession();

        sonDao = session.getSonDao();
        fatherDao = session.getFatherDao();
    }

    public void onAdd(View view) {
        add();
    }

    public void onDelete(View view) {
        del();
    }

    public void onUpdate(View view) {
        udate();
    }

    public void onSelect(View view) {
        select();
    }

    private void add() {
        Son son = new Son();
        son.setName("bunny son");
        son.setAge(22);
        Father father = new Father();
        father.setName("bunny father");
        father.setAge(46);
        long fatherId = fatherDao.insert(father);
        son.setFatherId(fatherId);
        sonDao.insert(son);
        Log.d(TAG, "add: " + son.getName());
        Log.d(TAG, "add: " + father.getName());
    }

    private void del() {
        List<Son> l = sonDao.queryBuilder().list();
        Log.d(TAG, "udate: l:" + l);
        Son son = l.get(0);
        sonDao.delete(son);
    }

    private void udate() {
        List<Son> l = sonDao.queryBuilder().list();
        Log.d(TAG, "udate: l:" + l);
        Son son = l.get(0);
        son.setName("修改后名称");
        sonDao.insertOrReplace(son);
    }


    private void select() {
        List<Son> l = sonDao.queryBuilder().list();
        Log.d(TAG, "select: l:" + l);

        Son son1 = sonDao.queryBuilder().where(SonDao.Properties.Name.eq("bunny")).unique();

        List d = sonDao.queryBuilder().where(SonDao.Properties.Name.like("bunny%")).list();

        List d1 = sonDao.queryBuilder().where(SonDao.Properties.Age.between(20, 30)).list();

        List d2 = sonDao.queryBuilder().where(SonDao.Properties.Age.gt(18)).list();

        List d3 = sonDao.queryBuilder().where(SonDao.Properties.Age.lt(20)).list();

        List d4 = sonDao.queryBuilder().where(SonDao.Properties.Age.notEq(20)).list();

        List d5 = sonDao.queryBuilder().where(SonDao.Properties.Age.ge(18)).list();//>=18

        List d6 = sonDao.queryBuilder().where(SonDao.Properties.Name.like("bunny%")).orderAsc(SonDao.Properties.Age).list();


        //原生sql查询
        List data = sonDao.queryBuilder().where(new WhereCondition.StringCondition(
                "FATHER_ID IN " +
                        "(SELECT _ID FROM FATHER WHERE AGE < 45)"
        )).list();

    }

    //多线程查询
    private void selectThread() {
        final Query query = sonDao.queryBuilder().build();

        new Thread() {
            @Override
            public void run() {
                super.run();
                List data = query.list();//错误
                Log.d(TAG, "run: " + data);

                List data1 = query.forCurrentThread().list();//正确
            }
        }.start();
    }

    private void selectone2one() {
        List<Son> l = sonDao.queryBuilder().list();

        for (Son son : l) {
            Log.d(TAG, "selectone2one: " + son.getFather().getName());
        }

    }


    private void adds() {
//        Son son = new Son();
//        son.setName("bunny son");
//        son.setAge(22);
//        sonDao.insert(son);
//
//
//        Father father = new Father();
//        father.setName("bunnys father");
//        father.setAge(23);
//        father.setSon(son);
//        fatherDao.insert(father);
//
//
//        Father father2 = new Father();
//        father2.setName("bunnys father");
//        father2.setAge(23);
//        father2.setSon(son);
//        fatherDao.insert(father2);
//
//        Log.d(TAG, "add: " + son.getName());
//        Log.d(TAG, "add: " + father.getName());
    }

    private void selectone2manybuild() {

    }

    private void selectone2many() {
//        List<Son> l = sonDao.queryBuilder().list();
//
//        for (Son son : l) {
//            List<Father> li = son.getFathers();
//            for (Father father : li) {
//                Log.d(TAG, "selectone2many: " + son.getName() + " father:" + father.getName());
//            }
//
//        }
    }

}
