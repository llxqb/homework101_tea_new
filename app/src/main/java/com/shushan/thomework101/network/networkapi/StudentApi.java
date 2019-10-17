package com.shushan.thomework101.network.networkapi;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by li.liu on 2019/05/28.
 * GuideApi
 */

public interface StudentApi {
    /**
     * 保存学生信息
     */
    @POST("teacher/student/student_detail")
    Observable<String> saveStudentInfo(@Body String request);
    /**
     * 提交辅导反馈
     */
    @POST("teacher/student/student_feedback")
    Observable<String> submitFeedbackInfo(@Body String request);
    /**
     * 根据融云第三方id获取用户头像和昵称
     */
    @POST("teacher/student/search_student")
    Observable<String> onRequestUserInfoByRid(@Body String request);

}
