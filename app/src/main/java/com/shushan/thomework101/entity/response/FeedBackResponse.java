package com.shushan.thomework101.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FeedBackResponse {


    /**
     * error : 0
     * msg : 成功
     * data : [{"id":56,"cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png","name":"学生111","version":"","coupon_id":0,"s_id":22,"end_time":1571450734,"grade":"五年级","feedback_time":1571456802,"third_id":"zuoye10122","start_time":1571450726,"status":1},{"id":57,"cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png","name":"学生111","version":"","coupon_id":0,"s_id":22,"end_time":1571455418,"grade":"五年级","feedback_time":0,"third_id":"zuoye10122","start_time":1571455399,"status":0},{"id":58,"cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png","name":"学生111","version":"","coupon_id":0,"s_id":22,"end_time":1571469150,"grade":"五年级","feedback_time":0,"third_id":"zuoye10122","start_time":1571469132,"status":0},{"id":59,"cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png","name":"学生111","version":"","coupon_id":0,"s_id":22,"end_time":1571469483,"grade":"五年级","feedback_time":0,"third_id":"zuoye10122","start_time":1571469471,"status":0},{"id":60,"cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png","name":"学生111","version":"","coupon_id":0,"s_id":22,"end_time":1571469891,"grade":"五年级","feedback_time":0,"third_id":"zuoye10122","start_time":1571469881,"status":0},{"id":61,"cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png","name":"学生111","version":"","coupon_id":0,"s_id":22,"end_time":1571469948,"grade":"五年级","feedback_time":0,"third_id":"zuoye10122","start_time":1571469940,"status":0},{"id":62,"cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png","name":"学生111","version":"","coupon_id":0,"s_id":22,"end_time":1571470018,"grade":"五年级","feedback_time":0,"third_id":"zuoye10122","start_time":1571470007,"status":0},{"id":63,"cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png","name":"学生111","version":"","coupon_id":0,"s_id":22,"end_time":1571470143,"grade":"五年级","feedback_time":1571470189,"third_id":"zuoye10122","start_time":1571470135,"status":1}]
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

    public static class DataBean implements Parcelable {
        /**
         * id : 56
         * cover : http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png
         * name : 学生111
         * version :
         * coupon_id : 0
         * s_id : 22
         * end_time : 1571450734
         * grade : 五年级
         * feedback_time : 1571456802
         * third_id : zuoye10122
         * start_time : 1571450726
         * status : 1
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

        protected DataBean(Parcel in) {
            id = in.readInt();
            cover = in.readString();
            name = in.readString();
            version = in.readString();
            coupon_id = in.readInt();
            s_id = in.readInt();
            end_time = in.readInt();
            grade = in.readString();
            feedback_time = in.readInt();
            third_id = in.readString();
            start_time = in.readInt();
            status = in.readInt();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(cover);
            dest.writeString(name);
            dest.writeString(version);
            dest.writeInt(coupon_id);
            dest.writeInt(s_id);
            dest.writeInt(end_time);
            dest.writeString(grade);
            dest.writeInt(feedback_time);
            dest.writeString(third_id);
            dest.writeInt(start_time);
            dest.writeInt(status);
        }
    }
}
