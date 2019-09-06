package com.shushan.thomework101.entity.constants;


import com.shushan.thomework101.R;

/**
 * Created by li.liu on 2017/12/18.
 */

public class Constant {
    /**
     * 公共弹框选择方式1和2,3,4
     */
    public static final int DIALOG_ONE = 1;
    public static final int DIALOG_TWO = 2;
    public static final int DIALOG_THREE = 3;
    public static final int DIALOG_FOUR = 4;
    public static final int DIALOG_FIVE = 5;

    //键盘延时100
    public static final int DELAYTIME = 100;

    //Google登录回调
    public static final int GOOGLE_LOGIN = 100;
    //facebook登录回调
    public static final int FACEBOOK_LOGIN = 64206;

    //图片类型 1头像2封面3相册4举报5消息
    public static final int PIC_AVATOR = 1;
    public static final int PIC_COVER = 2;
    public static final int PIC_ALBUM = 3;
    public static final int PIC_REPORT = 4;
    public static final int PIC_MESSAGE = 5;

    //占位图图片资源
    public static final int LOADING_SMALL = R.mipmap.ic_launcher;
    public static final int LOADING_MIDDLE = R.mipmap.ic_launcher;
    public static final int LOADING_BIG = R.mipmap.ic_launcher;
    public static final int LOADING_AVATOR = R.mipmap.ic_launcher;

    //发起google支付
    public static final int GOOGLE_PAY_REQ = 10001;

    //recyclerView item 中某一/某些控件刷新
    public static final String ITEM_UPDATE = "itemUpdate";
    //设备标识
    public static final String FROM = "Android";
}
