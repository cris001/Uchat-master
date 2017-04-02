package com.uchat.entity;

/**
 * Created by xuge on 2017/3/13.
 */
public class Chart {
    private int id;
    private String date;
    private int yon;

    public Chart(int id, String date, int yon) {
        this.id = id;
        this.date = date;
        this.yon = yon;
    }

    public Chart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getYon() {
        return yon;
    }

    public void setYon(int yon) {
        this.yon = yon;
    }
}
