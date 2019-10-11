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

    //---上传老师证书---//
    /**
     * 身份证正面
     */
    public String card_front;
    /**
     * 身份证反面
     */
    public String card_reverse;
    /**
     * 教师资格证
     */
    public String license;
    /**
     * 教师职称证书
     */
    public String evaluation;

    //---上传老师试讲视频---//
    public String video;
}
