package com.example.myapplication.page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMvvmBinding;
import com.example.myapplication.databinding.ActivityMvvmBindingImpl;
import com.example.myapplication.mvvm.model.ObSwordsman;
import com.example.myapplication.mvvm.model.Swordsman;

import java.util.Date;

/**
 * @Author wuyuhang
 * @Date 2022/8/17 21:55
 * @Describe 用于测试MVVM框架的页面
 */

public class MVVMActivity extends AppCompatActivity {
    public static final String TAG = "MVVMActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvmBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        Swordsman swordsman= new Swordsman("张无忌", "s");
        ObSwordsman obSwordsman = new ObSwordsman("任我行", "s");
        binding.setSwordsman(swordsman);
        binding.setObSwordsman(obSwordsman);
        binding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MVVMActivity.this, "data binding 点击事件", Toast.LENGTH_SHORT).show();
            }
        });
        binding.setDate(new Date());
        binding.setUpdateObSwordsman(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obSwordsman.setName("吴羽航");
            }
        });
        binding.setResetButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obSwordsman.setName("任我行");
            }
        });
        Log.e(TAG, "onCreate");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG, "onRestoreInstanceState");
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
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }
}