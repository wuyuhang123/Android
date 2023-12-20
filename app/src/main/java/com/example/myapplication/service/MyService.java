package com.example.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;

import com.example.myapplication.Book;
import com.example.myapplication.IBookManager;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {

    public static final String TAG = MyService.class.getSimpleName();
    public Handler handler;
    private final List<Book> books = new ArrayList<>();

    private IBookManager.Stub iBookManager = new IBookManager.Stub() {
        @Override
        public void addBook(Book inBook1, Book inBook2) throws RemoteException {
            Log.i(TAG, "service addBook" + inBook1.toString());
            books.add(inBook1);
        }
    };

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                stopSelf();
//                Log.e(TAG, "onBind: stopSelf");
//            }
//        }, 3000);
        return iBookManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler(Looper.getMainLooper());
        Log.e(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                stopSelf();
//                Log.e(TAG, "onStartCommand: stopSelf");
//            }
//        }, 3000);
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e(TAG, "onRebind");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind");
        return true;
    }
}