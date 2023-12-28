package com.example.myapplication.page;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.example.myapplication.R;

public class TestLeakCanaryActivity extends AppCompatActivity {

    public static final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_leak_canary);
        ImageView imageView = findViewById(R.id.image);
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.leak_canary_test));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("LeakCanaryActivity", "handle message");
            }
        }, 30000);
    }



}