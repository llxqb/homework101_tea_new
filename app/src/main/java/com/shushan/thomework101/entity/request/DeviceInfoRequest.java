package com.shushan.thomework101.entity.request;

import com.shushan.thomework101.entity.constants.Constant;

public class DeviceInfoRequest {
    public DeviceInfoRequest() {
        platform = Constant.FROM;
    }

    /**
     * 版本号
     */
    public String version;
    /**
     * 渠道
     */
    public String channel;
    /**
     * 手机型号
     */
    public String deviceType;
    /**
     * 设备id
     */
    public String deviceId;
    /**
     * 系统版本号
     */
    public String sysVer;
    /**
     * 平台
     */
    public String platform;
    //国家 可不传
    public String nation;
    //语言 可不传
    public String language;
    //可不传
    public String wifi;
}
