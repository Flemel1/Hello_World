package com.example.programandroid_1.receiver;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.programandroid_1.R;

public class WifiChangeReciever extends BroadcastReceiver {

    private int state;
    private Context mContext;
    private final String CHANNEL_ID = "1";
    private final String CHANNEL_NAME = "Notification_Wifi_State";
    private final String CHANNEL_DESC = "This is notification of wifi state";
    private NotificationManager mManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
        switch (state) {
            case WifiManager.WIFI_STATE_ENABLED :
                createNotification("enabled");
                break;
            case WifiManager.WIFI_STATE_DISABLED :
                createNotification("disabled");
                break;
        }
    }

    private void createNotification(String status) {
        NotificationCompat.Builder notif = new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Wifi State")
                .setContentText("Wifi state is " + status);
        mManager.notify(0, notif.build());
    }

    public void createNotificationChannel(NotificationManager manager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            );
            mManager = manager;
            mManager.createNotificationChannel(channel);
        }
    }
}
