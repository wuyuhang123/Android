package com.example.myapplication.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author wuyuhang
 * @Date 2023/12/4 14:19
 * @Describe 动态代理示意代码
 */
public class DynamicProxy implements InvocationHandler {

    public final Car car;

    public DynamicProxy(Car car) {
        this.car = car;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是动态代理，我在我的代理对象执行方法前说一句");
        Object result = method.invoke(car, args);
        return result;
    }
}
