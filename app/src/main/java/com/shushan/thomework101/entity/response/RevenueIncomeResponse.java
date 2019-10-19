package com.shushan.thomework101.entity.response;

import java.util.List;

public class RevenueIncomeResponse {

    /**
     * data : [{"cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png","create_time":1571414400,"money":"0.02","name":"学生111","pay_money":"1.00","type":1},{"cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png","create_time":1571414400,"money":"0.00","name":"学生111","pay_money":"1.00","type":1}]
     * error : 0
     * msg : 成功
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
         * cover : http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png
         * create_time : 1571414400
         * money : 0.02
         * name : 学生111
         * pay_money : 1.00
         * type : 1
         */

        private String cover;
        private int create_time;
        private String money;
        private String name;
        private String pay_money;
        private int type;

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
