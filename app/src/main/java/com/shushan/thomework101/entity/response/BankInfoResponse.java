package com.shushan.thomework101.entity.response;

import java.util.List;

public class BankInfoResponse {

    /**
     * error : 0
     * msg : 成功
     * data : [{"bank":"中国邮政储蓄银行","label":"PSBC"},{"bank":"中国民生银行","label":"CMBC"},{"bank":"中国银行","label":"BOC"},{"bank":"中国建设银行","label":"CCB"},{"bank":"华夏银行","label":"HXBANK"},{"bank":"中国光大银行","label":"CEB"},{"bank":"恒丰银行","label":"EGBANK"},{"bank":"中国农业银行","label":"ABC"},{"bank":"交通银行","label":"COMM"},{"bank":"兴业银行","label":"CIB"},{"bank":"浙商银行","label":"CZBANK"},{"bank":"渤海银行","label":"BOHAIB"},{"bank":"中国工商银行","label":"ICBC"},{"bank":"中信银行","label":"CITIC"},{"bank":"招商银行","label":"CMB"},{"bank":"其他银行","label":"BANK"}]
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
         * bank : 中国邮政储蓄银行
         * label : PSBC
         */

        private String bank;
        private String label;

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}
