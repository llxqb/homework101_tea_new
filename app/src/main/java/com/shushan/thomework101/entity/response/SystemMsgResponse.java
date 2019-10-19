package com.shushan.thomework101.entity.response;

import java.util.List;

public class SystemMsgResponse {
    /**
     * error : 0
     * msg : 成功
     * data : [{"id":1,"content":"这是一条系统消息","user_id":1,"state":2,"send_time":1571198196,"status":1}]
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
         * id : 1
         * content : 这是一条系统消息
         * user_id : 1
         * state : 2
         * send_time : 1571198196
         * status : 1
         */

        private int id;
        private String content;
        private int user_id;
        private int state;
        private int send_time;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getSend_time() {
            return send_time;
        }

        public void setSend_time(int send_time) {
            this.send_time = send_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
