package com.shushan.thomework101.entity.response;

import java.util.List;

public class RevenueIncomeResponse {
    /**
     * error : 0
     * msg : 成功
     * data : [{"name":"zy2790","pay_money":"4999.00","money":"4.90","type":2,"cover":""},{"name":"zy2790","pay_money":"4999.00","money":"40.00","type":1,"cover":""}]
     */

    private int error;
    private String msg;
    private List<DataBean> data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : zy2790
         * pay_money : 4999.00
         * money : 4.90
         * type : 2
         * cover :
         */

        private String name;
        private String pay_money;
        private String money;
        private int type;
        private String cover;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPay_money() {
            return pay_money;
        }

        public void setPay_money(String pay_money) {
            this.pay_money = pay_money;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }
}
