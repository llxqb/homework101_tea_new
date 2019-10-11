package com.shushan.thomework101.mvp.utils;

import com.google.gson.Gson;
import com.shushan.thomework101.entity.response.HomeResponse;
import com.shushan.thomework101.entity.response.LoginResponse;
import com.shushan.thomework101.entity.user.BuProcessor;
import com.shushan.thomework101.entity.user.User;

public class LoginUtils {
    /**
     * 保存User信息
     * PersonalInfoResponse 转换位LoginUser
     */
    public static User saveLoginUser(LoginResponse loginResponse) {
        User user = new User();
        user.token = loginResponse.getToken();
        user.subject = UserUtil.subjectStringToInt(loginResponse.getSubject());
        user.grades = loginResponse.getGrade_id();
        return user;
    }

    public static User updateLoginUser(HomeResponse.UserBean userBean, User user, BuProcessor buProcessor) {
        user.subject = UserUtil.subjectStringToInt(userBean.getSubject());
        user.grades = userBean.getGrade_id();
        user.guideTimeBean = userBean.getGuide_time();
        LogUtils.e("user:"+new Gson().toJson(user));
        buProcessor.setLoginUser(user);
        return buProcessor.getUser();
    }
}
