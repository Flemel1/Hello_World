package com.example.programandroid_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.NotificationManager;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.programandroid_1.adapter.ViewPagerAdapter;
import com.example.programandroid_1.databinding.ActivityHomeScreenBinding;
import com.example.programandroid_1.receiver.WifiChangeReciever;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeScreenBinding binding;
    WifiChangeReciever wifiReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        wifiReceiver = new WifiChangeReciever();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Toast.makeText(getApplicationContext(), "Selamat anda berhasil login", Toast.LENGTH_SHORT)
                .show();
        binding.viewPager.setAdapter(new ViewPagerAdapter(this, getSupportFragmentManager()));
        binding.tabs.setupWithViewPager(binding.viewPager);
        wifiReceiver.createNotificationChannel(manager);
        passWifiState();
    }

    private void passWifiState() {
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiReceiver, intentFilter);
    }
}