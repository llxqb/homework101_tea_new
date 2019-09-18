package com.shushan.thomework101.mvp.model;


import com.google.gson.Gson;
import com.shushan.thomework101.network.networkapi.StudentApi;

import javax.inject.Inject;

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

//    /**
//     * 登录
//     */
//    public Observable<ResponseData> onRequestLoginInfo(RegisterRequest request) {
//        if (!TextUtils.isEmpty(request.phoneNum) || !TextUtils.isEmpty(request.email)) {
//            //验证码登录
//            return mStudentApi.onRequestLoginByCode(request.phoneNum, request.email, request.code, request.clientType).map(mTransform::transformCommon);
//        } else {
//            //账号密码登录
//            return mStudentApi.onRequestLoginInfo(request.account, request.pwd, request.clientType).map(mTransform::transformCommon);
//        }
//    }
//
//    /**
//     * 注册
//     */
//    public Observable<ResponseData> onRequestRegister(RegisterRequest request) {
//        return mStudentApi.onRequestRegister(request.account, request.phoneNum, request.email, request.pwd, request.code, request.clientType).map(mTransform::transformCommon);
//    }

}
