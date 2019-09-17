package com.shushan.thomework101.mvp.model;


import com.google.gson.Gson;
import com.shushan.thomework101.network.networkapi.OtherApi;

import javax.inject.Inject;

/**
 * Created by li.liu on 2019/09/17.
 * app 其它部分
 */

public class OtherModel {
    private final OtherApi mOtherApi;
    private final Gson mGson;
    private final ModelTransform mTransform;

    @Inject
    public OtherModel(OtherApi api, Gson gson, ModelTransform transform) {
        mOtherApi = api;
        mGson = gson;
        mTransform = transform;
    }

//    /**
//     * 登录
//     */
//    public Observable<ResponseData> onRequestLoginInfo(RegisterRequest request) {
//        if (!TextUtils.isEmpty(request.phoneNum) || !TextUtils.isEmpty(request.email)) {
//            //验证码登录
//            return mOtherApi.onRequestLoginByCode(request.phoneNum, request.email, request.code, request.clientType).map(mTransform::transformCommon);
//        } else {
//            //账号密码登录
//            return mOtherApi.onRequestLoginInfo(request.account, request.pwd, request.clientType).map(mTransform::transformCommon);
//        }
//    }
//
//    /**
//     * 注册
//     */
//    public Observable<ResponseData> onRequestRegister(RegisterRequest request) {
//        return mOtherApi.onRequestRegister(request.account, request.phoneNum, request.email, request.pwd, request.code, request.clientType).map(mTransform::transformCommon);
//    }

}
