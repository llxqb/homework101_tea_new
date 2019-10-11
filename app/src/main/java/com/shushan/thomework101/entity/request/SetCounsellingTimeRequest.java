package com.shushan.thomework101.entity.request;

public class SetCounsellingTimeRequest {
    public String token;
    /**
     * 1,2,3,4,5
     */
    public String workday;
    /**
     * 8:00-24:00
     */
    public String work_time;
    /**
     * 6,7
     */
    public String off_day;
    /**
     * 8:00-24:00
     */
    public String off_time;
}
