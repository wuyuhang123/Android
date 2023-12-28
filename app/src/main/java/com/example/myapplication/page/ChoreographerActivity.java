package com.example.myapplication.page;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;

import com.example.myapplication.R;

public class ChoreographerActivity extends AppCompatActivity {

    public static final String TAG = "ChoreographerActivity";
    private long mLastTime;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choreographer);
        HandlerThread handlerThread = new HandlerThread("wyh");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        Handler mainHandler = new Handler(getMainLooper());
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
                    @Override
                    public void doFrame(long frameTimeNanos) {
                        long interval = frameTimeNanos - mLastTime;
                        if (interval > 1000000000) {
                            Log.i(TAG, String.valueOf(count));
                            count = 0;
                            mLastTime = frameTimeNanos;
                        } else {
                            count++;
                        }
                        Choreographer.getInstance().postFrameCallback(this);
                    }
                });
                mLastTime = System.nanoTime();
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}