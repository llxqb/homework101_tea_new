package com.shushan.thomework101.entity.request;

/**
 * 辅导反馈
 */
public class SubmitFeedbackRequest {
    public String token;
    /**
     * 辅导反馈列表页返回的id
     */
    public String id;
    /**
     * 优点
     */
    public String merit;
    /**
     * 缺点
     */
    public String defect;

}
