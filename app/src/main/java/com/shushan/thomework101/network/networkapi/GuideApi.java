package com.shushan.thomework101.network.networkapi;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by li.liu on 2019/05/28.
 * GuideApi
 */

public interface GuideApi {
    /**
     * 获取验证码
     */
    @POST("admin/code/get")
    Observable<String> onRequestVerifyCode(@Body String token);

    /**
     * 登录
     */
    @POST("teacher/user/login")
    Observable<String> onRequestLogin(@Body String request);

    /**
     * 注销账号
     */
    @POST("teacher/user/logout")
    Observable<String> onRequestLogout(@Body String request);
}
