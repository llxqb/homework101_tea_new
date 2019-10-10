package com.shushan.thomework101.mvp.utils;

import com.shushan.thomework101.entity.response.LoginResponse;
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

}
