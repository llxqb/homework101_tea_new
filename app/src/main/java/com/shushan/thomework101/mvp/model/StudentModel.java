package com.shushan.thomework101.mvp.model;


import com.google.gson.Gson;
import com.shushan.thomework101.entity.request.SaveStudentInfoRequest;
import com.shushan.thomework101.entity.request.SubmitFeedbackRequest;
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
     * 保存学生信息
     */
    public Observable<ResponseData> saveStudentInfo(SaveStudentInfoRequest request) {
        return mStudentApi.saveStudentInfo(new Gson().toJson(request)).map(mTransform::transformCommon);
    }
    /**
     * 提交辅导反馈
     */
    public Observable<ResponseData> submitFeedbackInfo(SubmitFeedbackRequest request) {
        return mStudentApi.submitFeedbackInfo(new Gson().toJson(request)).map(mTransform::transformCommon);
    }

}
