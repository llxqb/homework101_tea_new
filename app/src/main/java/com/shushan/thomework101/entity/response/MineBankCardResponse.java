package com.shushan.thomework101.entity.response;

import java.util.List;

/**
 * 我的银行卡
 */
public class MineBankCardResponse {
    /**
     * error : 0
     * msg : 成功
     * data : [{"card_no":"6217857500027181046","bank":"中国银行","label":"BOC"}]
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
         * card_no : 6217857500027181046
         * bank : 中国银行
         * label : BOC
         */

        private String card_no;
        private String bank;
        private String label;

        public String getCard_no() {
            return card_no;
        }

        public void setCard_no(String card_no) {
            this.card_no = card_no;
        }

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
