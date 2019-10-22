package com.shushan.thomework101.entity.response;

/**
 * ClassName: LoginResponse
 * Desciption: //注册
 * author: liuli
 * date: 2019-10-08
 */
public class RegisterResponse {

    /**
     * mobile : 13262253738
     * last_login_ip : 113.247.23.105
     * last_login_time : 1571733880
     * create_time : 1571733880
     * token : f47041a3a51d140973612b65007499c9
     * platform : android
     * deviceId : 00000000-1155-799b-1155-799b00000000
     * userport : 35480
     * t_id : 10
     * logid : 160
     * first : 1
     */

    private String mobile;
    private String last_login_ip;
    private int last_login_time;
    private int create_time;
    private String token;
    private String platform;
    private String deviceId;
    private String userport;
    private String t_id;
    private String logid;
    private int first;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLast_login_ip() {
        return last_login_ip;
    }

    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip;
    }

    public int getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(int last_login_time) {
        this.last_login_time = last_login_time;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserport() {
        return userport;
    }

    public void setUserport(String userport) {
        this.userport = userport;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }
}
