package com.uchat.entity;

/**
 * Created by xuge on 2017/3/7.
 */
public class Admin {
    private int id;
    private String loginname;
    private String loginpsw;
    private String username;
    private String createtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getLoginpsw() {
        return loginpsw;
    }

    public void setLoginpsw(String loginpsw) {
        this.loginpsw = loginpsw;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Admin(int id, String loginname, String loginpsw, String username, String createtime) {
        this.id = id;
        this.loginname = loginname;
        this.loginpsw = loginpsw;
        this.username = username;
        this.createtime = createtime;
    }

    public Admin(String loginname, String loginpsw, String username, String createtime) {
        this.loginname = loginname;
        this.loginpsw = loginpsw;
        this.username = username;
        this.createtime = createtime;
    }

    public Admin(int id, String loginpsw) {
        this.id = id;
        this.loginpsw = loginpsw;
    }

    public Admin(String loginname, String loginpsw) {
        this.loginname = loginname;
        this.loginpsw = loginpsw;
    }
    public Admin() {
    }

    public Admin(int id, String loginname, String username) {
        this.id = id;
        this.loginname = loginname;
        this.username = username;
    }
}
