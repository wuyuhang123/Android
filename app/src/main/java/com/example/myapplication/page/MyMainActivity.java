package com.example.myapplication.page;

import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.os.Process;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import androidx.core.view.LayoutInflaterCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.AIDLActivity;
import com.example.myapplication.R;
import com.example.myapplication.recycleView.adapter.PageListAdapter;
import com.example.myapplication.recycleView.data.PageData;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author wuyuhang
 * @Date 2022/8/17 21:58
 * @Describe 主页面，各测试页入口
 */

public class MyMainActivity extends AppCompatActivity {

    public static final String TAG = "MyMainActivity";
    private PageListAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new LayoutInflater.Factory2() {
            @Nullable
            @Override
            public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                long startTime = System.currentTimeMillis();
                TextView textView = null;
                if ("TextView".equals(name)) {
                    textView = (TextView) getDelegate().createView(parent, name, context, attrs);
                    Log.d(TAG, "textView create costTime: " + (System.currentTimeMillis() - startTime));
                }
                return textView;
            }

            @Nullable
            @Override
            public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                return null;
            }
        });
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        Log.e(TAG, String.valueOf(Process.myPid()));
        init();
    }

    private void init(){
//        setContentView(R.layout.activity_main);
        new AsyncLayoutInflater(this).inflate(R.layout.activity_main, null, new AsyncLayoutInflater.OnInflateFinishedListener() {
            @Override
            public void onInflateFinished(@NonNull View view, int resid, @Nullable ViewGroup parent) {
                setContentView(view);
                mRecyclerView = findViewById(R.id.main_recycle_view);
                mRecyclerView.setAdapter(mAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyMainActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.addItemDecoration(new MyItemDecoration(MyMainActivity.this, LinearLayoutManager.VERTICAL));
            }
        });
        mAdapter = new PageListAdapter(getData(), this);
        //该方法可以dump当前内存的对象至文件中
        try {
            Debug.dumpHprofData(getExternalCacheDir().getAbsolutePath() + "/dump");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //在此处添加各种测试用页面
    private List<PageData> getData(){
        List<PageData> list = new LinkedList<>();
        list.add(new PageData("PermissionActivity", PermissionActivity.class));
        list.add(new PageData("RecycleView Page", RecycleViewActivity.class));
        list.add(new PageData("MVVM Page", MVVMActivity.class));
        list.add(new PageData("Fragment Page", FragmentActivity.class));
        list.add(new PageData("TestService Page", TestServiceActivity.class));
        list.add(new PageData("CustomView Page", CustomViewActivity.class));
        list.add(new PageData("Rxjava Page", RxjavaActivity.class));
        list.add(new PageData("Choreographer Page", ChoreographerActivity.class));
        list.add(new PageData("AIDL Page", AIDLActivity.class));
        list.add(new PageData("TestLeakCanary Page", TestLeakCanaryActivity.class));
        return list;
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