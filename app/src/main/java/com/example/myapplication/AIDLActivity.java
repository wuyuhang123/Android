package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.example.myapplication.service.MyService;

public class AIDLActivity extends AppCompatActivity {

    private static final String TAG = AIDLActivity.class.getSimpleName();
    private int count;
    private IBookManager binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidlactivity);
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                binder = IBookManager.Stub.asInterface(service);
                try {
                    service.linkToDeath(new IBinder.DeathRecipient() {
                        @Override
                        public void binderDied() {
                            Log.e(TAG, "binderDied");
                        }
                    }, 0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book(String.valueOf(count), count);
                count++;
                try {
                    binder.addBook(book, book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "client send book" + book);
            }
        });
    }
}