package com.shushan.thomework101.entity.response;

public class StudentDetailInfoResponse {

    /**
     * remark :
     * version :
     * name : zy7469
     * cover :
     * subject : 0
     * id : 278
     * grade : 初二
     * status : 未付费
     * end_time : 0
     */

    private String remark;
    private String version;
    private String name;
    private String cover;
    private int subject;
    private int id;
    private String grade;
    private String status;
    private int end_time;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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
}
