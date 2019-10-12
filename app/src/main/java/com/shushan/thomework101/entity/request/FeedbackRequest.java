package com.shushan.thomework101.entity.request;

/**
 * 辅导反馈
 */
public class FeedbackRequest {
    public String token;
    /**
     * 当天年月日时间戳，不传默认当天
     */
    public String search_time;
    /**
     * 0全部 1免费体验学生 2月辅导 3季辅导 4年辅导 不传默认全部
     */
    public String state;
    public String page;
    public String pagesize;

}
