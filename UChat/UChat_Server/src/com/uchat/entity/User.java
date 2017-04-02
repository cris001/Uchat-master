package com.uchat.entity;

/**
 * Created by xuge on 2017/3/9.
 */
public class User {
    private String u_account;
    private String u_nickname;
    private String u_password;
    private String u_email;
    private String u_signature;
    private String u_birthday;
    private String u_phone;
    private String u_gender;
    private int u_permission;
    private String u_identification;

    public String getU_account() {
        return u_account;
    }

    public void setU_account(String u_account) {
        this.u_account = u_account;
    }

    public String getU_nickname() {
        return u_nickname;
    }

    public void setU_nickname(String u_nickname) {
        this.u_nickname = u_nickname;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_signature() {
        return u_signature;
    }

    public void setU_signature(String u_signature) {
        this.u_signature = u_signature;
    }

    public String getU_birthday() {
        return u_birthday;
    }

    public void setU_birthday(String u_birthday) {
        this.u_birthday = u_birthday;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_gender() {
        return u_gender;
    }

    public void setU_gender(String u_gender) {
        this.u_gender = u_gender;
    }

    public int getU_permission() {
        return u_permission;
    }

    public void setU_permission(int u_permission) {
        this.u_permission = u_permission;
    }

    public String getU_identification() {
        return u_identification;
    }

    public void setU_identification(String u_identification) {
        this.u_identification = u_identification;
    }

    public User(String u_account, String u_nickname, String u_password, String u_email, String u_signature, String u_birthday, String u_phone, String u_gender, int u_permission, String u_identification) {
        this.u_account = u_account;
        this.u_nickname = u_nickname;
        this.u_password = u_password;
        this.u_email = u_email;
        this.u_signature = u_signature;
        this.u_birthday = u_birthday;
        this.u_phone = u_phone;
        this.u_gender = u_gender;
        this.u_permission = u_permission;
        this.u_identification = u_identification;
    }

    public User() {
    }
}
