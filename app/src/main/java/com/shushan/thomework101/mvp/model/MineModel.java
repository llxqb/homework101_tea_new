package com.shushan.thomework101.mvp.model;


import com.google.gson.Gson;
import com.shushan.thomework101.network.networkapi.MineApi;

import javax.inject.Inject;

/**
 * Created by li.liu on 2019/09/17.
 * 我的相关
 */

public class MineModel {
    private final MineApi mMineApi;
    private final Gson mGson;
    private final ModelTransform mTransform;

    @Inject
    public MineModel(MineApi api, Gson gson, ModelTransform transform) {
        mMineApi = api;
        mGson = gson;
        mTransform = transform;
    }

//    /**
//     * 登录
//     */
//    public Observable<ResponseData> onRequestLoginInfo(RegisterRequest request) {
//        if (!TextUtils.isEmpty(request.phoneNum) || !TextUtils.isEmpty(request.email)) {
//            //验证码登录
//            return mMineApi.onRequestLoginByCode(request.phoneNum, request.email, request.code, request.clientType).map(mTransform::transformCommon);
//        } else {
//            //账号密码登录
//            return mMineApi.onRequestLoginInfo(request.account, request.pwd, request.clientType).map(mTransform::transformCommon);
//        }
//    }
//
//    /**
//     * 注册
//     */
//    public Observable<ResponseData> onRequestRegister(RegisterRequest request) {
//        return mMineApi.onRequestRegister(request.account, request.phoneNum, request.email, request.pwd, request.code, request.clientType).map(mTransform::transformCommon);
//    }

}
