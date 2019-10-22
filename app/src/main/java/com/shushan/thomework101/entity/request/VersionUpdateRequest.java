package com.shushan.thomework101.entity.request;

import com.shushan.thomework101.entity.constants.Constant;

public class VersionUpdateRequest {
    public VersionUpdateRequest() {
        system = Constant.FROM;
    }

    /**
     * 对应版本号
     */
    public String version;
    public String system;
}
