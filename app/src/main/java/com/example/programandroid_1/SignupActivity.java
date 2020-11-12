package com.example.programandroid_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.programandroid_1.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {
    public static final String EXTRA_USERNAME = "com.example.programandroid_1.EXTRA_USERNAME";
    public static final String EXTRA_PASSWORD = "com.example.programandroid_1.EXTRA_PASSWORD";

    private ActivitySignupBinding binding;
    private String usernameSignup;
    private String passwordSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void userSignup(View view) {
        usernameSignup = binding.edRegisUsername.getText().toString().trim();
        passwordSignup = binding.edRegisPassword.getText().toString().trim();
        Intent data = new Intent();
        data.putExtra(EXTRA_USERNAME, usernameSignup);
        data.putExtra(EXTRA_PASSWORD, passwordSignup);
        setResult(RESULT_OK, data);
        finish();
    }
}