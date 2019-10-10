package com.shushan.thomework101.network.networkapi;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by li.liu on 2019/05/28.
 * LoginApi
 */

public interface MineApi {
    /**
     * 更新用户个人信息
     */
    @POST("teacher/user/set_grade_subject")
    Observable<String> onRequestUploadPersonalInfo(@Body String request);
}
