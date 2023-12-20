package com.example.myapplication.proxy;

/**
 * @Author wuyuhang
 * @Date 2023/12/4 14:20
 * @Describe 静态代理示意代码
 */
public class StaticProxy implements Car {

    private final Car car;

    public StaticProxy(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        System.out.println("我是代理类，我要在我的代理对象的方法执行前说一句");
        car.run();
    }
}
