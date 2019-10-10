package com.shushan.thomework101.entity.response;

import java.util.List;

/**
 * ClassName: LoginResponse
 * Desciption: //登录
 * author: liuli
 * date: 2019-10-08
 */
public class LoginResponse {


    /**
     * tid : 4
     * name :
     * cover :
     * subject : 英语
     * grade_id : [2,5,7]
     * guide_time : {}
     * label : []
     * experience :
     * style :
     * idea :
     * state : 0
     * third_id : teacher1014
     * third_token : nSqzcfm6Ii/770mQDXXwLDXyKZnCDTW2UeEfJg0cj19I1IERdRco7JRiVcpgdHMi6PQtFlz45kz+u9BQ1+nyH1s9ZRj8rQqb
     * login_num : 3
     * wrong_login_num : 0
     * wrong_time : 0
     * card_front :
     * card_reverse :
     * license :
     * evaluation :
     * video :
     * card_state : 0
     * video_state : 0
     * train_state : 0
     * grade : ["二年级","五年级","初一"]
     * money : 0.00
     * logid : 55
     * token : fd74758f71934c000ec0eca316aa4ef5
     * first : 0
     */

    private int tid;
    private String name;
    private String cover;
    private String subject;
    private String grade_id;
    private GuideTimeBean guide_time;
    private String experience;
    private String style;
    private String idea;
    private int state;
    private String third_id;
    private String third_token;
    private int login_num;
    private int wrong_login_num;
    private int wrong_time;
    private String card_front;
    private String card_reverse;
    private String license;
    private String evaluation;
    private String video;
    private int card_state;
    private int video_state;
    private int train_state;
    private String money;
    private String logid;
    private String token;
    private int first;
    private List<String> label;
    private List<String> grade;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(String grade_id) {
        this.grade_id = grade_id;
    }

    public GuideTimeBean getGuide_time() {
        return guide_time;
    }

    public void setGuide_time(GuideTimeBean guide_time) {
        this.guide_time = guide_time;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getThird_id() {
        return third_id;
    }

    public void setThird_id(String third_id) {
        this.third_id = third_id;
    }

    public String getThird_token() {
        return third_token;
    }

    public void setThird_token(String third_token) {
        this.third_token = third_token;
    }

    public int getLogin_num() {
        return login_num;
    }

    public void setLogin_num(int login_num) {
        this.login_num = login_num;
    }

    public int getWrong_login_num() {
        return wrong_login_num;
    }

    public void setWrong_login_num(int wrong_login_num) {
        this.wrong_login_num = wrong_login_num;
    }

    public int getWrong_time() {
        return wrong_time;
    }

    public void setWrong_time(int wrong_time) {
        this.wrong_time = wrong_time;
    }

    public String getCard_front() {
        return card_front;
    }

    public void setCard_front(String card_front) {
        this.card_front = card_front;
    }

    public String getCard_reverse() {
        return card_reverse;
    }

    public void setCard_reverse(String card_reverse) {
        this.card_reverse = card_reverse;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getCard_state() {
        return card_state;
    }

    public void setCard_state(int card_state) {
        this.card_state = card_state;
    }

    public int getVideo_state() {
        return video_state;
    }

    public void setVideo_state(int video_state) {
        this.video_state = video_state;
    }

    public int getTrain_state() {
        return train_state;
    }

    public void setTrain_state(int train_state) {
        this.train_state = train_state;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public List<String> getLabel() {
        return label;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }

    public List<String> getGrade() {
        return grade;
    }

    public void setGrade(List<String> grade) {
        this.grade = grade;
    }

    public static class GuideTimeBean {
    }
}
