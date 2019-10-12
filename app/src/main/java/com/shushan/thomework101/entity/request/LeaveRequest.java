package com.shushan.thomework101.entity.request;

/**
 * 请假
 */
public class LeaveRequest {
    public String token;
    /**
     * 开始时间  年月日
     */
    public String start_time;
    public String end_time;
    /**
     * 天数
     */
    public String duration;
    /**
     * 原因
     */
    public String reason;
}
