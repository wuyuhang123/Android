package com.example.myapplication.network;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @Author wuyuhang
 * @Date 2023/11/26 17:47
 * @Describe
 */
public interface IpServiceForPost {
    @FormUrlEncoded
    @POST("getIpInfo.php")
    Observable<IpModel> getIpMsg(@Field("ip") String first);
}
