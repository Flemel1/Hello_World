package com.example.programandroid_1;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.programandroid_1.adapter.ViewPagerAdapter;
import com.example.programandroid_1.databinding.ActivityHomeScreenBinding;
import com.example.programandroid_1.receiver.WifiChangeReciever;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.btn_logout : logoutFromApplication(); break;

        }
        return true;
    }

    private void logoutFromApplication() {
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.com_example_myapp_PREFERENCE_LOGIN_FILE_KEY),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getString(R.string.com_example_myapp_PREFERENCE_LOGIN_FILE_KEY),
                            false);
        editor.apply();
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}