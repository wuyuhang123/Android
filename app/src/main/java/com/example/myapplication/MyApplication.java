package com.example.myapplication;

import android.app.Application;
import android.content.Context;

/**
 * @Author wuyuhang
 * @Date 2023/12/19 23:41
 * @Describe
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }
}
