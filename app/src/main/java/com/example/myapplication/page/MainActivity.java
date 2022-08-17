package com.example.myapplication.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Process;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.recycleView.adapter.PageListAdapter;
import com.example.myapplication.recycleView.data.PageData;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author wuyuhang
 * @Date 2022/8/17 21:58
 * @Describe 主页面，各测试页入口
 */

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private PageListAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        Log.e(TAG, String.valueOf(Process.myPid()));
        init();
    }

    private void init(){
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.main_recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PageListAdapter(getData(), this);
        mRecyclerView.setAdapter(mAdapter);
        // TODO: 2022/8/17 需要补充各item之间的分割线
    }

    //在此处添加各种测试用页面
    private List<PageData> getData(){
        List<PageData> list = new LinkedList<>();
        list.add(new PageData("RecycleView Page", RecycleViewActivity.class));
        list.add(new PageData("MVVM Page", MVVMActivity.class));
        list.add(new PageData("Fragment Page", FragmentActivity.class));
        return list;
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