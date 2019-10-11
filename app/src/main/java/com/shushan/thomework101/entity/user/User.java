package com.shushan.thomework101.entity.user;

import java.io.Serializable;

/**
 * Created by li.liu on 2018/1/29.
 * 保存登陆信息
 * sp保存对象必须序列化
 */

public class User implements Serializable {

    public String token;
    /**
     * 姓名
     */
    public String name;
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
     * 认证流程状态
     */
    public int registerState;
    /**
     * 我的标签
     */
    public String label1;
    public String label2;
}
