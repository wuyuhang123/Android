package com.example.myapplication;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * @Author wuyuhang
 * @Date 2023/12/7 18:35
 * @Describe
 */
public class ExecutorsDemo {

    public void test() {
        Executors.newFixedThreadPool(33).submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });
    }
}
