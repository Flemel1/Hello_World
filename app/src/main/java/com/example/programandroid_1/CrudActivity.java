package com.example.programandroid_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.programandroid_1.databinding.ActivityCrudBinding;
import com.example.programandroid_1.fragment.FragmentInput;
import com.example.programandroid_1.fragment.FragmentListData;
import com.example.programandroid_1.fragment.FragmentRoot;

public class CrudActivity extends AppCompatActivity {

    ActivityCrudBinding mBinding;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private final String TAG = "CrudActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityCrudBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(mBinding.frameLayout.getId(), new FragmentRoot());
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }
}