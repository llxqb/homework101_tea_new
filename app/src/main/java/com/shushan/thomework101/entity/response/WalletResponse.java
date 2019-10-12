package com.shushan.thomework101.entity.response;

/**
 *我的钱包
 */
public class WalletResponse {
    /**
     * money : 0
     * predict_money : 0
     * amort_money : 0
     * withdraw_money : 0
     */

    private int money;
    private int predict_money;
    private int amort_money;
    private int withdraw_money;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPredict_money() {
        return predict_money;
    }

    public void setPredict_money(int predict_money) {
        this.predict_money = predict_money;
    }

    public int getAmort_money() {
        return amort_money;
    }

    public void setAmort_money(int amort_money) {
        this.amort_money = amort_money;
    }

    public int getWithdraw_money() {
        return withdraw_money;
    }

    public void setWithdraw_money(int withdraw_money) {
        this.withdraw_money = withdraw_money;
    }
}
