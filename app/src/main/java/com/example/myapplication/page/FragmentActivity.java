package com.example.myapplication.page;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.R;

/**
 * @Author wuyuhang
 * @Date 2022/8/17 21:57
 * @Describe 用于测试Fragment的页面
 */
public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }
}