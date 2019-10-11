package com.shushan.thomework101.entity.response;

import java.io.Serializable;
import java.util.List;

public class HomeResponse {


    /**
     * user : {"tid":4,"name":"","cover":"","subject":"英语","grade_id":"[2,5,7]","guide_time":{"workday":"1,2,3,4,5","work_time":"16:00-23:59","off_day":"7","off_time":"00:00-23:59"},"label":[],"experience":"","style":"","idea":"","state":0,"third_id":"teacher1014","third_token":"nSqzcfm6Ii/770mQDXXwLDXyKZnCDTW2UeEfJg0cj19I1IERdRco7JRiVcpgdHMi6PQtFlz45kz+u9BQ1+nyH1s9ZRj8rQqb","login_num":7,"card_front":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191010/5d9f1cb5a930f.png","card_reverse":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191010/5d9f1cdcb0dba.png","license":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/student/20191010/5d9f1cf144401.png","evaluation":"","video":"http://newzuoye101.oss-cn-beijing.aliyuncs.com/teacher/video/20191011/caa9e4ac401c1675e108a1aa7bc897","card_state":2,"video_state":2,"train_state":2,"grade":["二年级","五年级","初一"],"money":"0.00","guide_state":0,"leave":0}
     * income : {"today_push_money":0,"today_class_fee":0,"today_income":0}
     * student : {"all":0,"pay":0,"today_pay":0}
     * order : []
     * earnings : {"already_money":0,"predict_money":0}
     */

    private UserBean user;
    private IncomeBean income;
    private StudentBean student;
    private EarningsBean earnings;
    private List<?> order;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public IncomeBean getIncome() {
        return income;
    }

    public void setIncome(IncomeBean income) {
        this.income = income;
    }

    public StudentBean getStudent() {
        return student;
    }

    public void setStudent(StudentBean student) {
        this.student = student;
    }

    public EarningsBean getEarnings() {
        return earnings;
    }

    public void setEarnings(EarningsBean earnings) {
        this.earnings = earnings;
    }

    public List<?> getOrder() {
        return order;
    }

    public void setOrder(List<?> order) {
        this.order = order;
    }

    public static class UserBean {
        /**
         * tid : 4
         * name :
         * cover :
         * subject : 英语
         * grade_id : [2,5,7]
         * guide_time : {"workday":"1,2,3,4,5","work_time":"16:00-23:59","off_day":"7","off_time":"00:00-23:59"}
         * label : []
         * experience :
         * style :
         * idea :
         * state : 0
         * third_id : teacher1014
         * third_token : nSqzcfm6Ii/770mQDXXwLDXyKZnCDTW2UeEfJg0cj19I1IERdRco7JRiVcpgdHMi6PQtFlz45kz+u9BQ1+nyH1s9ZRj8rQqb
         * login_num : 7
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
         * guide_state : 0
         * leave : 0
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
        private String card_front;
        private String card_reverse;
        private String license;
        private String evaluation;
        private String video;
        private int card_state;
        private int video_state;
        private int train_state;
        private String money;
        private int guide_state;
        private int leave;
        private List<?> label;
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

        public int getGuide_state() {
            return guide_state;
        }

        public void setGuide_state(int guide_state) {
            this.guide_state = guide_state;
        }

        public int getLeave() {
            return leave;
        }

        public void setLeave(int leave) {
            this.leave = leave;
        }

        public List<?> getLabel() {
            return label;
        }

        public void setLabel(List<?> label) {
            this.label = label;
        }

        public List<String> getGrade() {
            return grade;
        }

        public void setGrade(List<String> grade) {
            this.grade = grade;
        }

        public static class GuideTimeBean implements Serializable {
            /**
             * workday : 1,2,3,4,5
             * work_time : 16:00-23:59
             * off_day : 7
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

    public static class IncomeBean {
        /**
         * today_push_money : 0
         * today_class_fee : 0
         * today_income : 0
         */

        private int today_push_money;
        private int today_class_fee;
        private int today_income;

        public int getToday_push_money() {
            return today_push_money;
        }

        public void setToday_push_money(int today_push_money) {
            this.today_push_money = today_push_money;
        }

        public int getToday_class_fee() {
            return today_class_fee;
        }

        public void setToday_class_fee(int today_class_fee) {
            this.today_class_fee = today_class_fee;
        }

        public int getToday_income() {
            return today_income;
        }

        public void setToday_income(int today_income) {
            this.today_income = today_income;
        }
    }

    public static class StudentBean {
        /**
         * all : 0
         * pay : 0
         * today_pay : 0
         */

        private int all;
        private int pay;
        private int today_pay;

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }

        public int getPay() {
            return pay;
        }

        public void setPay(int pay) {
            this.pay = pay;
        }

        public int getToday_pay() {
            return today_pay;
        }

        public void setToday_pay(int today_pay) {
            this.today_pay = today_pay;
        }
    }

    public static class EarningsBean {
        /**
         * already_money : 0
         * predict_money : 0
         */

        private int already_money;
        private int predict_money;

        public int getAlready_money() {
            return already_money;
        }

        public void setAlready_money(int already_money) {
            this.already_money = already_money;
        }

        public int getPredict_money() {
            return predict_money;
        }

        public void setPredict_money(int predict_money) {
            this.predict_money = predict_money;
        }
    }
}
