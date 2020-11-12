package com.example.programandroid_1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.programandroid_1.databinding.ActivityMainBinding;
import com.example.programandroid_1.entity.User;
import com.example.programandroid_1.viewmodel.UserFactory;
import com.example.programandroid_1.viewmodel.ViewModelUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private  ActivityMainBinding binding;
    private ViewModelUser viewModelUser;
    private UserFactory userFactory;
    public List<User> list = new ArrayList<>();
    public static final int SIGNUP_REQUEST = 1;
    private SharedPreferences sharedPreferencesLoginStatus;
    private Boolean loginStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        sharedPreferencesLoginStatus = getSharedPreferences(
                        getString(R.string.com_example_myapp_PREFERENCE_LOGIN_FILE_KEY),
                        Context.MODE_PRIVATE);
        loginStatus = sharedPreferencesLoginStatus.getBoolean(
                getString(R.string.com_example_myapp_PREFERENCE_LOGIN_FILE_KEY),
                false);
        if (!loginStatus) {
            userFactory = new UserFactory(getApplication());
            viewModelUser = ViewModelProviders.of(this, userFactory).get(ViewModelUser.class);
            viewModelUser.getAllUser().observe(this, new Observer<List<User>>() {
                @Override
                public void onChanged(List<User> users) {
                    list = users;
                }
            });
            binding.btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = binding.edUsername.getText().toString().trim();
                    String password = binding.edPassword.getText().toString().trim();

                    for (User user : list) {
                        if (username.equals(user.getUsername()) && password.equals(user.getPassword())){
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            SharedPreferences.Editor editor = sharedPreferencesLoginStatus.edit();
                            editor.putBoolean(getString(R.string.com_example_myapp_PREFERENCE_LOGIN_FILE_KEY),
                                    true);
                            editor.apply();
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "username atau password anda tidak benar!", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                }
            });
        }
        else {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGNUP_REQUEST && resultCode == RESULT_OK) {
            String newUsername = data.getStringExtra(SignupActivity.EXTRA_USERNAME);
            String newPassword = data.getStringExtra(SignupActivity.EXTRA_PASSWORD);
            User user = new User(newUsername, newPassword);
            viewModelUser.insert(user);
        }
    }

    public void gotoSignupActivity(View view) {
        Intent intentSignup = new Intent(MainActivity.this, SignupActivity.class);
        startActivityForResult(intentSignup, SIGNUP_REQUEST);
    }
}