package com.shushan.thomework101.entity.user;

import java.io.Serializable;

/**
 * Created by li.liu on 2018/1/29.
 * 保存登陆信息
 * sp保存对象必须序列化
 */

public class User implements Serializable {
    public User() {
        isReadProtocol = false;
    }

    public String token;
    /**
     * 是否阅读了注册协议
     */
    public boolean isReadProtocol;
    /**
     * 融云id
     */
    public String rId;
    /**
     * 融云token
     */
    public String rToken;
    /**
     * 姓名
     */
    public String name;
    /**
     * 头像
     */
    public String cover;
    /**
     * 辅导科目
     * 单选
     */
    public int subject;
    /**
     * 年级（1-9 ）  最多选择三个年级
     * 集合
     */
    public String grades;

    /**
     * 辅导时间 Gson字符串对象
     */
    public String coachingTime;

    /**
     * 我的标签 Gson字符串对象
     */
    public String labels;
    /**
     * 是否通过审核
     */
    public boolean checkPass;
    /**
     * 教学经历
     */
    public String teachingExperience;
    /**
     * 教学风格
     */
    public String teachingStyle;
    /**
     * 教学理念
     */
    public String teachingPhilosophy;


}
