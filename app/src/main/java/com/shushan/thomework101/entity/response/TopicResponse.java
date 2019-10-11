package com.shushan.thomework101.entity.response;

/**
 * 试讲题目
 */
public class TopicResponse {
    /**
     * id : 1
     * grade : 4
     * question : 这是题目
     * explain : 这是讲解
     */

    private int id;
    private int grade;
    private String question;
    private String explain;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
