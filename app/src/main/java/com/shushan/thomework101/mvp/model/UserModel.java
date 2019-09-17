package com.shushan.thomework101.mvp.model;


import com.google.gson.Gson;
import com.shushan.thomework101.network.networkapi.UserApi;

import javax.inject.Inject;

/**
 * Created by li.liu on 2019/09/17.
 * 用户信息相关
 */

public class UserModel {
    private final UserApi mUserApi;
    private final Gson mGson;
    private final ModelTransform mTransform;

    @Inject
    public UserModel(UserApi api, Gson gson, ModelTransform transform) {
        mUserApi = api;
        mGson = gson;
        mTransform = transform;
    }

//    /**
//     * 登录
//     */
//    public Observable<ResponseData> onRequestLoginInfo(RegisterRequest request) {
//        if (!TextUtils.isEmpty(request.phoneNum) || !TextUtils.isEmpty(request.email)) {
//            //验证码登录
//            return mUserApi.onRequestLoginByCode(request.phoneNum, request.email, request.code, request.clientType).map(mTransform::transformCommon);
//        } else {
//            //账号密码登录
//            return mUserApi.onRequestLoginInfo(request.account, request.pwd, request.clientType).map(mTransform::transformCommon);
//        }
//    }
//
//    /**
//     * 注册
//     */
//    public Observable<ResponseData> onRequestRegister(RegisterRequest request) {
//        return mUserApi.onRequestRegister(request.account, request.phoneNum, request.email, request.pwd, request.code, request.clientType).map(mTransform::transformCommon);
//    }

}
