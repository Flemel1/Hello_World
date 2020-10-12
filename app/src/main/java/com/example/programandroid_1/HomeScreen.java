package com.example.programandroid_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Log.d("msg","msg");
        Toast.makeText(getApplicationContext(), "Selamat anda berhasil login", Toast.LENGTH_SHORT)
                .show();
    }
}