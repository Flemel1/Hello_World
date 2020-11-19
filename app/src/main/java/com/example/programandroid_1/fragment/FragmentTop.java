package com.example.programandroid_1.fragment;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.programandroid_1.CrudActivity;
import com.example.programandroid_1.HomeActivity;
import com.example.programandroid_1.MyJobService;
import com.example.programandroid_1.R;


public class FragmentTop extends Fragment implements View.OnClickListener {

    private final String TAG = "FragmentTop";

    private Button btnStartJob;
    private Button btnStopJob;
    private Button btnCRUD;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnStartJob = view.findViewById(R.id.btn_start_job);
        btnStopJob = view.findViewById(R.id.btn_stop_job);
        btnCRUD = view.findViewById(R.id.btn_crud);
        btnStartJob.setOnClickListener(this);
        btnStopJob.setOnClickListener(this);
        btnCRUD.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_start_job : startedJob(); break;
            case R.id.btn_stop_job : stoppedJob(); break;
            case R.id.btn_crud : moveToCrudActivity(); break;
        }
    }

    private void moveToCrudActivity() {
        Intent intent = new Intent(getActivity(), CrudActivity.class);
        startActivity(intent);
    }

    private void stoppedJob() {
        JobScheduler scheduler = (JobScheduler) getActivity().getSystemService(
                Context.JOB_SCHEDULER_SERVICE
        );
        scheduler.cancel(123);
        Log.i(TAG, "Job cancelled");
    }

    private void startedJob() {
        ComponentName componentName = new ComponentName(getContext().getApplicationContext(), MyJobService.class);
        JobInfo toastInfo = new JobInfo.Builder(123, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();
        JobScheduler scheduler = (JobScheduler) getActivity().getSystemService(
                Context.JOB_SCHEDULER_SERVICE
                 );
        int resultCode = scheduler.schedule(toastInfo);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.i(TAG, "Job Scheduled");
        }
        else {
            Log.i(TAG, "Job scheduling failed");
        }
    }
}