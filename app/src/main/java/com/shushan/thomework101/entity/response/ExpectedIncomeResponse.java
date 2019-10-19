package com.shushan.thomework101.entity.response;

import java.util.List;

/**
 * 预计收益
 */
public class ExpectedIncomeResponse {

    /**
     * all : 0.7
     * list : [{"name":"学生111","pay_money":"1.00","money":"0.60","create_time":1571284119,"type":1,"status":1,"cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png"},{"name":"学生111","pay_money":"1.00","money":"0.10","create_time":1571284119,"type":1,"status":1,"cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png"}]
     */

    private double all;
    private List<ListBean> list;

    public double getAll() {
        return all;
    }

    public void setAll(double all) {
        this.all = all;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * name : 学生111
         * pay_money : 1.00
         * money : 0.60
         * create_time : 1571284119
         * type : 1
         * status : 1
         * cover : http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png
         */

        private String name;
        private String pay_money;
        private String money;
        private int create_time;
        private int type;
        private int status;
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

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }
}
