package com.shushan.thomework101.entity.response;

/**
 * 试讲题目
 */
public class TopicResponse {

    /**
     * id : 52
     * subject : 2
     * grade : 3
     * question : http://newzuoye101.oss-cn-beijing.aliyuncs.com/question/15725111943005.png
     * width : 1125
     * height : 276
     */

    private int id;
    private int subject;
    private int grade;
    private String question;
    private int width;
    private int height;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
