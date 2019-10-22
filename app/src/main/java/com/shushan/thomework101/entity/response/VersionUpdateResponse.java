package com.shushan.thomework101.entity.response;

import java.util.List;

public class VersionUpdateResponse {

    /**
     * app_version : 1.0.3
     * app_des : ["更新部分","优化页面"]
     * update_url : https://zy.zuoye101.com/download/app-shushan-debug-teacher.apk
     * label : 1
     */

    private String app_version;
    private String update_url;
    private int label;
    private List<String> app_des;

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getUpdate_url() {
        return update_url;
    }

    public void setUpdate_url(String update_url) {
        this.update_url = update_url;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public List<String> getApp_des() {
        return app_des;
    }

    public void setApp_des(List<String> app_des) {
        this.app_des = app_des;
    }
}
