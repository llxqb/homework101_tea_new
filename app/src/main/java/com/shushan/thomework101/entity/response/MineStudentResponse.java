package com.shushan.thomework101.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 我的学生
 */
public class MineStudentResponse {
    /**
     * error : 0
     * msg : 成功
     * data : [{"id":12,"t_id":4,"s_id":12,"version":"人教版","remark":"刚刚更换花好几万块的话哈哈镜个","grade_id":5,"create_time":1570707023,"name":"zy7053","grade":"五年级","cover":"","status":"未付费","end_time":0},{"id":13,"t_id":4,"s_id":18,"version":"","remark":"","grade_id":7,"create_time":1570845600,"name":"zy啊啊啊","grade":"初一","cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191015/5da5ba9aec501.png","status":"未付费","end_time":0},{"id":22,"t_id":4,"s_id":19,"version":"","remark":"","grade_id":2,"create_time":1570858345,"name":"zy3492","grade":"二年级","cover":"","status":"未付费","end_time":0},{"id":25,"t_id":4,"s_id":20,"version":"","remark":"","grade_id":2,"create_time":1570858737,"name":"子非鱼","grade":"二年级","cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191012/5da17d598da54.png","status":"未付费","end_time":0},{"id":27,"t_id":4,"s_id":21,"version":"","remark":"","grade_id":5,"create_time":1571106146,"name":"charis","grade":"五年级","cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191015/5da53e0551da7.png","status":"未付费","end_time":0},{"id":30,"t_id":4,"s_id":21,"version":"","remark":"","grade_id":2,"create_time":1571106213,"name":"charis","grade":"二年级","cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191015/5da53e0551da7.png","status":"未付费","end_time":0},{"id":33,"t_id":4,"s_id":22,"version":"","remark":"","grade_id":5,"create_time":1571110237,"name":"学生111","grade":"五年级","cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191018/5da98eff7d4fc.png","status":"月辅导","end_time":1573876119},{"id":38,"t_id":4,"s_id":23,"version":"","remark":"","grade_id":7,"create_time":1571138137,"name":"zy7055","grade":"初一","cover":"","status":"未付费","end_time":0},{"id":42,"t_id":4,"s_id":24,"version":"","remark":"","grade_id":5,"create_time":1571190300,"name":"孙楠","grade":"五年级","cover":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191016/5da6769f7d1c0.png","status":"未付费","end_time":0},{"id":45,"t_id":4,"s_id":25,"version":"","remark":"","grade_id":5,"create_time":1571215716,"name":"zy8390","grade":"五年级","cover":"","status":"未付费","end_time":0},{"id":49,"t_id":4,"s_id":26,"version":"","remark":"","grade_id":5,"create_time":1571217342,"name":"zy8399","grade":"五年级","cover":"","status":"未付费","end_time":0},{"id":53,"t_id":4,"s_id":27,"version":"","remark":"","grade_id":5,"create_time":1571217756,"name":"zy8398","grade":"五年级","cover":"","status":"未付费","end_time":0}]
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
        public DataBean() {
        }

        /**
         * id : 12
         * t_id : 4
         * s_id : 12
         * version : 人教版
         * remark : 刚刚更换花好几万块的话哈哈镜个
         * grade_id : 5
         * create_time : 1570707023
         * name : zy7053
         * grade : 五年级
         * cover :
         * status : 未付费
         * end_time : 0
         */

        private int id;
        private int t_id;
        private int s_id;
        private String version;
        private String remark;
        private int grade_id;
        private int create_time;
        private String name;
        private String grade;
        private String cover;
        private String status;
        private int end_time;

        protected DataBean(Parcel in) {
            id = in.readInt();
            t_id = in.readInt();
            s_id = in.readInt();
            version = in.readString();
            remark = in.readString();
            grade_id = in.readInt();
            create_time = in.readInt();
            name = in.readString();
            grade = in.readString();
            cover = in.readString();
            status = in.readString();
            end_time = in.readInt();
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

        public int getT_id() {
            return t_id;
        }

        public void setT_id(int t_id) {
            this.t_id = t_id;
        }

        public int getS_id() {
            return s_id;
        }

        public void setS_id(int s_id) {
            this.s_id = s_id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getGrade_id() {
            return grade_id;
        }

        public void setGrade_id(int grade_id) {
            this.grade_id = grade_id;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
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

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeInt(t_id);
            dest.writeInt(s_id);
            dest.writeString(version);
            dest.writeString(remark);
            dest.writeInt(grade_id);
            dest.writeInt(create_time);
            dest.writeString(name);
            dest.writeString(grade);
            dest.writeString(cover);
            dest.writeString(status);
            dest.writeInt(end_time);
        }
    }
}
