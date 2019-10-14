package com.shushan.thomework101.entity.request;

/**
 * 绑定银行卡
 */
public class BindCardRequest {
    public String token;
    /**
     * 真实姓名
     */
    public String realname;
    /**
     * 银行卡号
     */
    public String card_no;
    /**
     * 银行
     */
    public String bank;
    /**
     * 银行标识
     */
    public String label;
}
