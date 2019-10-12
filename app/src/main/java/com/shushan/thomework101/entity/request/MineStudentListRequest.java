package com.shushan.thomework101.entity.request;

/**
 * 我的学生列表
 */
public class MineStudentListRequest {
    public String token;
    /**
     * 1已付费 2月辅导 3季辅导 4年辅导 5未付费 6今日新增 7三日新增 8七日新增
     */
    public String label;
}
