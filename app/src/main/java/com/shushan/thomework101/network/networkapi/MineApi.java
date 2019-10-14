package com.shushan.thomework101.network.networkapi;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by li.liu on 2019/05/28.
 * LoginApi
 */

public interface MineApi {
    /**
     * 上传图片
     */
    @POST("student/upload")
    Observable<String> uploadImageRequest(@Body String request);
    /**
     * 上传视频（文件）
     */
    @Multipart
    @POST("teacher/upload/video")
    Observable<String> uploadVideoRequest(@Part MultipartBody.Part video);

    /**
     * 设置老师辅导年级和科目
     */
    @POST("teacher/user/set_grade_subject")
    Observable<String> uploadPersonalGradeInfo(@Body String request);
    /**
     * 上传老师证书
     */
    @POST("teacher/user/set_certificate")
    Observable<String> uploadPersonalCardInfo(@Body String request);
    /**
     * 上传试讲视频
     */
    @POST("teacher/user/set_trial")
    Observable<String> uploadPersonalVideoInfo(@Body String request);
    /**
     * 获取试讲题目
     */
    @POST("teacher/user/trial")
    Observable<String> onRequestTopicInfo(@Body String request);
    /**
     * 设置辅导时间
     */
    @POST("teacher/user/set_guide_time")
    Observable<String> setCounsellingTime(@Body String request);
    /**
     * 完善个人资料
     */
    @POST("teacher/user/complete_userinfo")
    Observable<String> uploadPersonalInfo(@Body String request);
    /**
     * 修改个人资料
     */
    @POST("teacher/user/setinfo")
    Observable<String> updatePersonalInfo(@Body String request);
    /**
     * 请假
     */
    @POST("teacher/user/leave")
    Observable<String> onRequestLeave(@Body String request);
    /**
     * 预计收益
     */
    @POST("teacher/wallet/predict_earnings")
    Observable<String> onRequestExpectedIncome(@Body String request);
    /**
     * 已到手金额明细
     */
    @POST("teacher/wallet/income")
    Observable<String> onRequestRevenueIncome(@Body String request);


}
