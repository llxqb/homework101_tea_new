package com.shushan.thomework101.mvp.utils;

import com.google.gson.Gson;
import com.shushan.thomework101.entity.response.HomeResponse;
import com.shushan.thomework101.entity.response.LoginResponse;
import com.shushan.thomework101.entity.user.BuProcessor;
import com.shushan.thomework101.entity.user.User;

import java.util.List;

public class LoginUtils {
    /**
     * 保存User信息
     * PersonalInfoResponse 转换位LoginUser
     */
    public static User saveLoginUser(LoginResponse loginResponse) {
        User user = new User();
        user.token = loginResponse.getToken();
        user.rId = loginResponse.getThird_id();
        user.rToken = loginResponse.getThird_token();
        user.subject = UserUtil.subjectStringToInt(loginResponse.getSubject());
        user.grades = loginResponse.getGrade_id();
        user.isReadProtocol = true;
        return user;
    }

    public static User updateLoginUser(HomeResponse.UserBean userBean, User user, BuProcessor buProcessor) {
        user.name = userBean.getName();
        user.subject = UserUtil.subjectStringToInt(userBean.getSubject());
        user.grades = userBean.getGrade_id();
        user.cover = userBean.getCover();
        user.labels = ListToString(userBean.getLabel());
        user.coachingTime = new Gson().toJson(userBean.getGuide_time());
        user.checkPass = userBean.getState() == 1;
        user.teachingExperience = userBean.getExperience();
        user.teachingStyle = userBean.getStyle();
        user.teachingPhilosophy = userBean.getIdea();
        buProcessor.setLoginUser(user);
        return buProcessor.getUser();
    }

    /**
     * 集合转字符串
     */
    private static String ListToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == null || list.get(i).equals("")) {
                    continue;
                }
                sb.append(list.get(i)).append(",");
            }
            return sb.toString().substring(0, sb.toString().length() - 1);
        } else {
            return "";
        }
    }


}
