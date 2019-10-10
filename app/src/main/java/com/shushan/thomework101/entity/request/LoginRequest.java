package com.shushan.thomework101.entity.request;


import com.shushan.thomework101.entity.constants.Constant;

/**
 * 登录request
 */
public class LoginRequest {
    public LoginRequest() {
        this.platform = Constant.FROM;
    }

    public String mobile;
    public String code;
    /**
     * 渠道 android 或者ios
     */
    private String platform;
    /**
     * 设备唯一标识
     */
    public String deviceId;
    public String version;
}
