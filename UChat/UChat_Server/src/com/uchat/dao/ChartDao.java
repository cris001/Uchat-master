package com.uchat.dao;

import java.util.List;

import com.uchat.entity.Chart;
/**
 * Created by xuge on 2017/3/13.
 */
public interface ChartDao {
    public List<Chart> selectforday(String date);
    public List<Chart> select();
    public boolean add(String date, int index);
    public int selectdate(String date);

}
