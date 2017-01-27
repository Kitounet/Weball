package com.weball.benoit.weball.service;

import android.animation.TimeAnimator;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


import com.weball.benoit.weball.NetworkActivity;
import com.weball.benoit.weball.R;
import com.weball.benoit.weball.requestClass.NotificationAnswer;


import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by benoi on 13/06/2016.
 */
public class NotificationService extends Service{
    private NotificationAnswer      myNotification = null;
    private String    token;
    private NotificationManager            mNM             = null;
    private int NOTIFICATION_ID = 45785;

    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        super.onStartCommand(intent, flags, startId);
        mNM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        token = intent.getStringExtra("UserInfo");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run(){
                WeballService service = ServiceFactory.createRetrofitService(WeballService.class, WeballService.ENDPOINT);
                service.getNotifications(token)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<NotificationAnswer>() {
                            @Override
                            public final void onCompleted() {
                                // do nothing
                            }

                            @Override
                            public final void onError(Throwable e) {
                                Log.e("Weball", e.getMessage());
                            }

                            @Override
                            public final void onNext(NotificationAnswer response) {
                                if (response != null && myNotification !=  response) {
                                    myNotification  = response;
                                    List<NotificationAnswer.Request> myR = response.getRequests();

                                    for (int i = 0; i < myR.size(); i++)
                                    {
                                        createNotification(myR.get(i));
                                    }
                                }
                            }
                        });

            }
        }, 60000);
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }


    private void createNotification(NotificationAnswer.Request request){
        String name = request.getUser().getFullName();
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.logo_weball)
                        .setContentTitle("Demande de coéquipier")
                        .setContentText(name + " vous a ajouté");

        Intent targetIntent = new Intent(this, NetworkActivity.class);
        targetIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        targetIntent.putExtra("token", token);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        mNM.notify(NOTIFICATION_ID, builder.build());
    }


    private void deleteNotification(){
        final NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        //la suppression de la notification se fait grâce a son ID
        notificationManager.cancel(NOTIFICATION_ID);
    }
}
