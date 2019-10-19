package com.shushan.thomework101.entity.response;

import java.util.List;

public class FeedBackResponse {

    /**
     * error : 0
     * msg : 成功
     * data : [{"id":52,"cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png","name":"学生111","version":"","coupon_id":0,"s_id":22,"end_time":1571387131,"grade":"五年级","feedback_time":0,"third_id":"zuoye10122","start_time":1571387043,"status":0},{"id":53,"cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png","name":"学生111","version":"","coupon_id":0,"s_id":22,"end_time":1571396685,"grade":"五年级","feedback_time":0,"third_id":"zuoye10122","start_time":1571396676,"status":0},{"id":54,"cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png","name":"学生111","version":"","coupon_id":0,"s_id":22,"end_time":1571396760,"grade":"五年级","feedback_time":0,"third_id":"zuoye10122","start_time":1571396733,"status":0},{"id":55,"cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png","name":"学生111","version":"","coupon_id":0,"s_id":22,"end_time":1571397848,"grade":"五年级","feedback_time":0,"third_id":"zuoye10122","start_time":1571397843,"status":0}]
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
         * id : 52
         * cover : http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png
         * name : 学生111
         * version :
         * coupon_id : 0
         * s_id : 22
         * end_time : 1571387131
         * grade : 五年级
         * feedback_time : 0
         * third_id : zuoye10122
         * start_time : 1571387043
         * status : 0
         */

        private int id;
        private String cover;
        private String name;
        private String version;
        private int coupon_id;
        private int s_id;
        private int end_time;
        private String grade;
        private int feedback_time;
        private String third_id;
        private int start_time;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(int coupon_id) {
            this.coupon_id = coupon_id;
        }

        public int getS_id() {
            return s_id;
        }

        public void setS_id(int s_id) {
            this.s_id = s_id;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public int getFeedback_time() {
            return feedback_time;
        }

        public void setFeedback_time(int feedback_time) {
            this.feedback_time = feedback_time;
        }

        public String getThird_id() {
            return third_id;
        }

        public void setThird_id(String third_id) {
            this.third_id = third_id;
        }

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
