package com.shushan.thomework101.entity.response;

/**
 *我的钱包
 */
public class WalletResponse {

    /**
     * w_id : 4
     * money : 0.00
     * predict_money : 0.60
     * amort_money : 0.10
     * withdraw_money : 0.00
     * t_id : 4
     */

    private int w_id;
    private String money;
    private String predict_money;
    private String amort_money;
    private String withdraw_money;
    private int t_id;

    public int getW_id() {
        return w_id;
    }

    public void setW_id(int w_id) {
        this.w_id = w_id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPredict_money() {
        return predict_money;
    }

    public void setPredict_money(String predict_money) {
        this.predict_money = predict_money;
    }

    public String getAmort_money() {
        return amort_money;
    }

    public void setAmort_money(String amort_money) {
        this.amort_money = amort_money;
    }

    public String getWithdraw_money() {
        return withdraw_money;
    }

    public void setWithdraw_money(String withdraw_money) {
        this.withdraw_money = withdraw_money;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }
}
