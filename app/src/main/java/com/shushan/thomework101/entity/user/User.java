package com.shushan.thomework101.entity.user;

import java.io.Serializable;

/**
 * Created by li.liu on 2018/1/29.
 * 保存登陆信息
 * sp保存对象必须序列化
 */

public class User implements Serializable {

    public User() {
        userType = 1;//默认男非VIP
    }

    public int uid;
    public String nickname;
    //头像
    public String trait;
    //封面
    public String cover;
    /**
     * 1男2女
     */
    public int sex;
    public String birthday;
    public String city;
    //交友宣言
    public String declaration;
    /**
     * 0 非vip 1 vip
     */
    public int vip;
    public int vip_time;
    /**
     * 0 非svip 1 svip svip没有到期时间
     */
    public int svip;
    public String height;
    public String weight;
    /**
     * 胸围
     */
    public String bust;
    /**
     * 职业
     */
    public String occupation;
    //嗨豆数
    public int beans;
    public String token;
    public int age;
    public int forbidden;
    /**
     * 最小推送年龄
     */
    public String pushing_small_age;
    /**
     * 最大推送年龄
     */
    public String pushing_large_age;
    //推送性别   推送性别 0不限1男2女
    public int pushing_gender;
    /**
     * 曝光次数
     */
    public int exposure;
    public int last_login_time;
    /**
     * 联系方式
     */
    public String contact;
    /**
     * 个人标签
     */
    public String label;


    //下面是新增首页用户信息
    /**
     * 曝光类型
     */
    public int exposure_type;
    /**
     * 曝光时长
     */
    public int exposure_time;
    /**
     * 今日已免费喜欢数
     */
    public int today_like;
    /**
     * 今日已免费聊天数
     */
    public int today_chat;
    /**
     * 今日已免费查看联系方式数
     */
    public int today_see_contact;
    /**
     * 用户类型
     * app 自己添加
     * 1：男非VIP
     * 2：男VIP
     * 3：超级VIP
     * 4：女非VIP
     * 5：女VIP
     */
    public int userType;
    /**
     * 封面视频上传状态
     * 0正常1审核中2审核不通过
     */
    public int state;

    /**
     *新增喜欢我的人
     */
    public int newLikeCount;
    /**
     * 最新喜欢我的人头像
     */
    public String newLikeTrait;
    /**
     * 1有新增喜欢的人 0没有
     */
    public int newLikeState;


}
