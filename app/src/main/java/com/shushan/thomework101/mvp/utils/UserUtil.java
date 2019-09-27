package com.shushan.thomework101.mvp.utils;

import android.text.TextUtils;

/**
 * 用户util
 */
public class UserUtil {
    /**
     * 根据选择的科目 判断是否是初中科目
     *
     * @param subject 科目
     */
    public static boolean isJuniorHighSchoolSubject(String subject) {
        if (!TextUtils.isEmpty(subject)) {
            return !subject.equals("语文") && !subject.equals("数学") && !subject.equals("英语");
        }
        return false;//默认返回全部科目
    }

}
