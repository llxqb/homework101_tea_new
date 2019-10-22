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
     * name : 李白
     * cover : http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191011/5da04f4796072.png
     * subject : 英语
     * grade_id : [2,5,7]
     * guide_time : {"workday":"1,2,3,4,5","work_time":"16:00-23:59","off_day":"6","off_time":"00:00-23:59"}
     * label : ["幽默","风趣"]
     * experience : 啊啊a
     * style : bbbb
     * idea : 就是考试开始就是抗生素男男生男生女石家庄计算机视觉设计设计师设计开始年终奖怎么做看着看着
     * state : 1
     * third_id : teacher1014
     * third_token : nSqzcfm6Ii/770mQDXXwLDXyKZnCDTW2UeEfJg0cj19I1IERdRco7JRiVcpgdHMi6PQtFlz45kz+u9BQ1+nyH1s9ZRj8rQqb
     * login_num : 26
     * wrong_login_num : 0
     * wrong_time : 0
     * card_front : http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191010/5d9f1cb5a930f.png
     * card_reverse : http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191010/5d9f1cdcb0dba.png
     * license : http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191010/5d9f1cf144401.png
     * evaluation :
     * video : http://newzuoye101.oss-cn-beijing.aliyuncs.com/teacher/video/20191011/caa9e4ac401c1675e108a1aa7bc897
     * card_state : 2
     * video_state : 2
     * train_state : 2
     * grade : ["二年级","五年级","初一"]
     * money : 0.00
     * logid : 154
     * token : 9d3c3b0bb50a90619d6049339142473d
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
        /**
         * workday : 1,2,3,4,5
         * work_time : 16:00-23:59
         * off_day : 6
         * off_time : 00:00-23:59
         */

        private String workday;
        private String work_time;
        private String off_day;
        private String off_time;

        public String getWorkday() {
            return workday;
        }

        public void setWorkday(String workday) {
            this.workday = workday;
        }

        public String getWork_time() {
            return work_time;
        }

        public void setWork_time(String work_time) {
            this.work_time = work_time;
        }

        public String getOff_day() {
            return off_day;
        }

        public void setOff_day(String off_day) {
            this.off_day = off_day;
        }

        public String getOff_time() {
            return off_time;
        }

        public void setOff_time(String off_time) {
            this.off_time = off_time;
        }
    }
}
