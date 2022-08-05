package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class ActivityA extends AppCompatActivity {

    private static final String TAG = "ActivityA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        Log.e(TAG, String.valueOf(Process.myPid()));
        setContentView(R.layout.activity_a);
        Button b = findViewById(R.id.button3);
        PackageManager packageManager = getPackageManager();
        List<PackageInfo> list = packageManager.getInstalledPackages(0);
        List<ApplicationInfo> list1 = packageManager.getInstalledApplications(0);
        for (int i = 0; i < list.size(); i++){
            PackageInfo packageInfo = list.get(i);
            if ("com.example.myapplication2".equals(packageInfo.packageName)){
                Log.e(TAG, "true");
            }
        }
        for (int i = 0; i < list.size(); i++){
            ApplicationInfo applicationInfo = list1.get(i);
            if ("com.example.myapplication2".equals(applicationInfo.packageName)){
                Log.e(TAG, "true");
            }
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityA.this, MainActivityA.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }
}