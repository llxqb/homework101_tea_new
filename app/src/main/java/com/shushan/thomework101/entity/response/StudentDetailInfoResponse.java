package com.shushan.thomework101.entity.response;

public class StudentDetailInfoResponse {

    /**
     * remark : 备注
     * version : 2
     * name : zy2790
     * cover :
     * subject : 0
     * status : 月辅导
     * end_time : 1572310930
     * id : 1
     */

    private String remark;
    private String version;
    private String name;
    private String cover;
    private int subject;
    private String status;
    private int end_time;
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
