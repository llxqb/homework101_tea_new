package com.shushan.thomework101.entity.response;

/**
 *提现
 */
public class WithdrawResponse {


    /**
     * bank : {"bank":"交通银行","card_no":"6222620640019079232","label":"COMM"}
     */

    private BankBean bank;

    public BankBean getBank() {
        return bank;
    }

    public void setBank(BankBean bank) {
        this.bank = bank;
    }

    public static class BankBean {
        /**
         * bank : 交通银行
         * card_no : 6222620640019079232
         * label : COMM
         */

        private String bank;
        private String card_no;
        private String label;

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getCard_no() {
            return card_no;
        }

        public void setCard_no(String card_no) {
            this.card_no = card_no;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}
