package com.shushan.thomework101.mvp.model;


import com.google.gson.Gson;
import com.shushan.thomework101.entity.request.FeedbackIdRequest;
import com.shushan.thomework101.entity.request.SaveStudentInfoRequest;
import com.shushan.thomework101.entity.request.StudentDetailInfoRequest;
import com.shushan.thomework101.entity.request.UserInfoByRidRequest;
import com.shushan.thomework101.network.networkapi.StudentApi;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by li.liu on 2019/09/18.
 * 学生模块
 */

public class StudentModel {
    private final StudentApi mStudentApi;
    private final Gson mGson;
    private final ModelTransform mTransform;

    @Inject
    public StudentModel(StudentApi api, Gson gson, ModelTransform transform) {
        mStudentApi = api;
        mGson = gson;
        mTransform = transform;
    }

    /**
     * 查询学生信息
     */
    public Observable<ResponseData> onRequestStudentInfo(StudentDetailInfoRequest request) {
        return mStudentApi.onRequestStudentInfo(new Gson().toJson(request)).map(mTransform::transformCommon);
    }

    /**
     * 保存学生信息
     */
    public Observable<ResponseData> saveStudentInfo(SaveStudentInfoRequest request) {
        return mStudentApi.saveStudentInfo(new Gson().toJson(request)).map(mTransform::transformCommon);
    }

    /**
     * 根据融云第三方id获取用户头像和昵称
     */
    public Observable<ResponseData> onRequestUserInfoByRid(UserInfoByRidRequest request) {
        return mStudentApi.onRequestUserInfoByRid(mGson.toJson(request)).map(mTransform::transformCommon);
    }

    /**
     * 学生结束辅导，老师去反馈，查询反馈id
     */
    public Observable<ResponseData> onRequestFeedBackId(FeedbackIdRequest request) {
        return mStudentApi.onRequestFeedBackId(mGson.toJson(request)).map(mTransform::transformCommon);
    }
}
