package com.example.programandroid_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.programandroid_1.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Log.d("msg", "msg");
        Toast.makeText(getApplicationContext(), "Selamat anda berhasil login", Toast.LENGTH_SHORT)
                .show();
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(this, getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }
}