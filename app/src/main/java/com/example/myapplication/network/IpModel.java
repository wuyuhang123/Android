package com.example.myapplication.network;

/**
 * @Author wuyuhang
 * @Date 2023/11/26 17:49
 * @Describe
 */
public class IpModel<T> {
    private int code;
    private T data;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
