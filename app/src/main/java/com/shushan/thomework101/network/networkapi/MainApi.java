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
     * 检查版本更新
     */
    @POST("teacher/version/app_version")
    Observable<String> onRequestVersionUpdate(@Body String request);

    /**
     * 上传设备信息
     */
    @POST("teacher/device")
    Observable<String> uploadDeviceInfo(@Body String request);

    /**
     * 请求homeFragment list 数据
     */
    @POST("teacher")
    Observable<String> onRequestHomeInfo(@Body String request);

    /**
     * 请求我的学生 list 数据
     */
    @POST("teacher/student")
    Observable<String> onRequestMineStudentInfo(@Body String request);

    /**
     * 请求我的学生 list 数据
     */
    @POST("teacher/student/feedback")
    Observable<String> onRequestFeedbackInfo(@Body String request);

    /**
     * 提交辅导反馈
     */
    @POST("teacher/student/student_feedback")
    Observable<String> submitFeedbackInfo(@Body String request);
}
