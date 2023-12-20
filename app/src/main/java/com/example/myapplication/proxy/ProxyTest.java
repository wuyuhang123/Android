package com.example.myapplication.proxy;

import java.lang.reflect.Proxy;

/**
 * @Author wuyuhang
 * @Date 2023/12/4 14:21
 * @Describe 代理模式测试主函数
 */
public class ProxyTest {
    public static void main(String[] args) throws Exception {
        //动态代理
        final CarImpl carImpl = new CarImpl();
        Car dynamicCar = (Car) Proxy.newProxyInstance(carImpl.getClass().getClassLoader(), carImpl.getClass().getInterfaces(), new DynamicProxy(carImpl));
        Car staticCar = new StaticProxy(new CarImpl());
        dynamicCar.run();
        staticCar.run();
    }
}
