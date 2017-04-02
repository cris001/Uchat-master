package com.uchat.entity;

/**
 * Created by starwill on 2017/3/20.
 */
public class Student {
    private String student_id;
    private String nickname;
    private int sign_in;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSign_in() {
        return sign_in;
    }

    public void setSign_in(int sign_in) {
        this.sign_in = sign_in;
    }
}
