package com.example.asus.servicetest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by asus on 2016/5/18.
 */
public class MyService extends Service {

    private static final int HELLO_ID = 1;
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private DownloadBinder mBinder =new DownloadBinder();
    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d("Myservice","startDownload executed");
        }
        public int getProgress(){
            Log.d("Myservice","getProgress executed");
            return 0;
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
       /* Notification notification =new Notification(R.drawable.cat,"Notification comes",System.currentTimeMillis());
        Intent notificationIntent =new Intent (this,MainActivity.class);
        PendingIntent pendingIntent =PendingIntent.getActivities(this,0,notificationIntent,PendingIntent.FLAG_IMMUTABLE);
        notification.setLatestEventInfo(this,"This is title","This is content",pendingIntent);

        startForeground(1,notification);*/
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder=  new NotificationCompat.Builder(this);
        Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        mBuilder.setContentTitle("My notification")//设置通知栏标题
                .setContentText("Hello World!") //设置通知栏显示内容
                .setContentIntent(contentIntent) //设置通知栏点击意图
                .setTicker("hello ,you have a notification") //通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setSmallIcon(R.drawable.cat);

        mNotificationManager.notify(HELLO_ID, mBuilder.build());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Myservice","adsada");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy()
    {

        super.onDestroy();
        Log.d("Myservice","onDestroy excuted");
    }
}
