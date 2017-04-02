package com.uchat.dao.impl;

import com.uchat.dao.BaseDao;
import com.uchat.utils.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.uchat.entity.Chart;
import com.uchat.dao.ChartDao;

/**
 * Created by xuge on 2017/3/13.
 */
public class ChartDaoImpl extends BaseDao implements ChartDao {
    public List<Chart> selectforday(String sdate) {//获取集合
        conn = Util.getConnection();
        List<Chart> list = new ArrayList<Chart>();
        String sql = "select * from chart where date like '" + sdate + "%'";
        Object[] obj = {};
        try {
            ResultSet rs = query(sql, obj);//获取结果集
            while (rs.next()) {
                int id = rs.getInt(1);
                String date = rs.getString(2);
                int yon = rs.getInt(3);
                Chart chart1 = new Chart(id, date, yon);
                list.add(chart1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {//释放资源
            Util.close(conn, ps, rs);
        }
        System.out.println(list.size());
        return list;
    }


    public List<Chart> select() {//获取集合
        conn = Util.getConnection();
        List<Chart> list = new ArrayList<Chart>();
        String sql = "select * from chart";
        Object[] obj = {};
        try {
            ResultSet rs = query(sql, obj);//获取结果集
            while (rs.next()) {
                int id = rs.getInt(1);
                String date = rs.getString(2);
                int yon = rs.getInt(3);
                Chart chart1 = new Chart(id, date, yon);
                list.add(chart1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {//释放资源
            Util.close(conn, ps, rs);
        }
        System.out.println(list.size());
        return list;

    }

    public boolean add(String date, int index) {
        conn = Util.getConnection();
        boolean flag = false;
        if (index == 1) {
            String sql = "update chart set yon=yon+1 where date=?";
            Object[] obj = {date};
            if (conn != null) {
                if (update(sql, obj) != -1) {
                    flag = true;
                }
            }
            Util.close(conn, ps, rs);
        } else {
            String sql = "insert into chart(date, yon) values(?,1)";
            Object[] obj = {date};

            if (conn != null) {
                if (update(sql, obj) != -1) {
                    flag = true;
                }
                Util.close(conn, ps, rs);
            }
        }
        return flag;
    }

    public int selectdate(String date){
        conn=Util.getConnection();
        String sql="select * from chart where date=?";
        int flag=0;
        Object[] obj={date};
        try {
            ResultSet rs=query(sql, obj);
            while(rs.next()){
                flag=1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{//释放资源
            Util.close(conn, ps, rs);
        }
        return flag;
    }

}
