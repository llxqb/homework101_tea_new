package com.shushan.thomework101.entity.request;

/**
 * 上传个人信息
 */
public class UploadPersonalInfoRequest {
    public String token;
    /**
     * 科目
     */
    public String subject;
    /**
     * 年级 Json 数据
     */
    public String grades;
}
