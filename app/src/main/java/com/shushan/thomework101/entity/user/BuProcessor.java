package com.shushan.thomework101.entity.user;

import android.content.Context;
import android.text.TextUtils;

import com.shushan.thomework101.entity.constants.SpConstant;
import com.shushan.thomework101.mvp.utils.SharePreferenceUtil;

import javax.inject.Inject;

/**
 * BuProcessor
 */
public class BuProcessor {
    //    private LoginUser mLoginUser = new LoginUser();
    private final SharePreferenceUtil mSharePreferenceUtil;
    private User mUser;

    @Inject
    public BuProcessor(Context context, SharePreferenceUtil mSharePreferenceUtil) {
        this.mSharePreferenceUtil = mSharePreferenceUtil;

    }

    public boolean isValidLogin() {
        mUser = (User) mSharePreferenceUtil.readObjData("user");
        return mUser != null && !TextUtils.isEmpty(mUser.token);
    }


    public String getToken() {
        mUser = (User) mSharePreferenceUtil.readObjData("user");
        return mUser != null ? mUser.token : null;
    }

    public User getUser() {
        return (User) mSharePreferenceUtil.readObjData("user");
    }

    public void setLoginUser(User mUser) {
        mSharePreferenceUtil.saveObjData(SpConstant.LOGIN_USER, mUser);
    }


    /**
     * 验证是否第一次完善资料
     * 选择年级和选择科目
     */
    public boolean isFinishFirstWrite() {
        mUser = (User) mSharePreferenceUtil.readObjData("user");
        return mUser != null && !TextUtils.isEmpty(mUser.subject) && !TextUtils.isEmpty(mUser.grades);
    }


    public User reSetUserData() {
        // 恢复用户相关
//        Object o1 = mSharePreferenceUtil.readObjData(SpConstant.LOGIN_USER);
//        if (o1 != null && o1 instanceof LoginUser) {
//            mLoginUser = (LoginUser) o1;
//            return mLoginUser;
//        }
        return null;
    }

    //退出登录清除数据
    public void clearLoginUser() {
        // 清空用户
        User mUser = (User) mSharePreferenceUtil.readObjData("user");
        if (mUser != null) {
            mSharePreferenceUtil.saveObjData(SpConstant.LOGIN_USER, "");
        }
    }
}
