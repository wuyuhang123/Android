package com.example.myapplication.network;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Author wuyuhang
 * @Date 2023/11/26 17:47
 * @Describe
 */
public interface IpServiceForPost {

    @GET("self")
    Observable<IpModel<IpData>> getIpMsg(@Query("ip") String first,
                                         @Query("app_id") String appId,
                                         @Query("app_secret") String appSecret);
}
