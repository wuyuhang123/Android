package com.example.myapplication.proxy;

/**
 * @Author wuyuhang
 * @Date 2023/12/4 14:29
 * @Describe
 */
public class CarImpl implements Car{
    @Override
    public void run() {
        System.out.println("嘟嘟嘟");
    }
}
