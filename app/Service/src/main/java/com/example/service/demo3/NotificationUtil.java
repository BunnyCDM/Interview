package com.example.service.demo3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.service.R;
import com.example.service.demo1.MainActivity01;
import com.example.service.demo2.MyService02;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mac on 2020-06-22.
 * <p>
 * 通知工具类
 */
public class NotificationUtil {

    NotificationManager mNotificationManager;
    private Map<Integer, Notification> mNotifications;
    private Context mContext = null;

    public NotificationUtil(Context context) {
        //获取通知系统服务
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //创建通知集合
        mNotifications = new HashMap<>();

        mContext = context;
    }

    /**
     * 显示通知
     */
    public void showNotification() {

        Notification notification = new Notification();
        //设置滚动文字
        notification.tickerText = "tickerText";
        //设置显示时间
        notification.when = System.currentTimeMillis();
        //设置图标
        notification.icon = R.mipmap.ic_launcher;
        //设置通知特性
        notification.flags = Notification.FLAG_AUTO_CANCEL;


        //设置点击通知栏操作
        Intent intent = new Intent(mContext, MainActivity01.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
        notification.contentIntent = pendingIntent;

        //创建RemoteViews对象
//        RemoteViews remoteViews=new RemoteViews(mContext.getPackageName(),R.layout.activity_linear_layout);

        //设置开始按钮操作
//        Intent intentStart=new Intent(mContext, MyService02.class);
//        remoteViews.setOnClickPendingIntent(R.id.btnStartService,);
        //设置结束按钮操作

        //设置Notification的视图
//        notification.contentView=remoteViews;

        mNotificationManager.notify(1,notification);
//        mNotifications.put(1,notification)

    }

    /**
     * 取消通知
     *
     * @param id
     */
    public void cancelNotification(int id) {
        mNotificationManager.cancel(id);
//        mNotifications.remove(id);
    }


    /**
     * 更新进度条
     *
     * @param id
     * @param progress
     */
    public void updateNotification(int id, int progress) {

    }
}
