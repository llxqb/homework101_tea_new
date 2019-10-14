package com.shushan.thomework101.entity.response;

import java.util.List;

public class StudentChangeRecordResponse {

    /**
     * error : 0
     * msg : 成功
     * data : [{"create_time":1570676612,"reason":"讲解不仔细","name":"zy2790","grade":"四年级","guide_name":"月辅导","status":1}]
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
         * create_time : 1570676612
         * reason : 讲解不仔细
         * name : zy2790
         * grade : 四年级
         * guide_name : 月辅导
         * status : 1
         */

        private int create_time;
        private String reason;
        private String name;
        private String grade;
        private String guide_name;
        private int status;

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getGuide_name() {
            return guide_name;
        }

        public void setGuide_name(String guide_name) {
            this.guide_name = guide_name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
