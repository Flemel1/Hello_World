package com.example.programandroid_1;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;


public class MyJobService extends JobService {
    private final String TAG = "MyJobService";
    private boolean jobCancelled = false;
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i(TAG, "Job started");
        doBackground(params);
        return true;
    }

    private void doBackground(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (jobCancelled) {
                        jobFinished(params, false);
                        return;
                    }
                    Handler mHander = new Handler(getMainLooper());
                    mHander.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Job is running",
                                    Toast.LENGTH_SHORT
                            ).show();
                            Log.i(TAG, "running");
                        }
                    });
                    try {
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(TAG, "Job stopped");
        jobCancelled = true;
        return true;
    }
}
