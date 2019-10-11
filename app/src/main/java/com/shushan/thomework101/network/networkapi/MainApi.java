package com.shushan.thomework101.network.networkapi;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by helei on 2017/4/27.
 * MainApi
 */

public interface MainApi {

    /**
     * 请求homeFragment list 数据
     */
    @POST("teacher")
    Observable<String> onRequestHomeInfo(@Body String request);

}
