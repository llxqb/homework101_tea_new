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
     * 查询学生信息
     */
    @POST("teacher/student/detail")
    Observable<String> onRequestStudentInfo(@Body String request);
    /**
     * 保存学生信息
     */
    @POST("teacher/student/student_detail")
    Observable<String> saveStudentInfo(@Body String request);
    /**
     * 根据融云第三方id获取用户头像和昵称
     */
    @POST("teacher/student/search_student")
    Observable<String> onRequestUserInfoByRid(@Body String request);
    /**
     * 学生结束辅导，老师去反馈，查询反馈id
     */
    @POST("teacher/student/search_guideid")
    Observable<String> onRequestFeedBackId(@Body String request);

}
