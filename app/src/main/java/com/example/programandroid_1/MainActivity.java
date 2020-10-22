package com.example.programandroid_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.programandroid_1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private  ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.edUsername.getText().toString().trim();
                String password = binding.edPassword.getText().toString().trim();

                if (username.equals("admin") && password.equals("admin")){
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "username atau password anda tidak benar!", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }
}