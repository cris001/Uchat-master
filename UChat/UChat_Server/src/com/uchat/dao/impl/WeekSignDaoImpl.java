package com.uchat.dao.impl;



import com.uchat.dao.WeekSignDao;
import com.uchat.entity.WeekSign;
import com.uchat.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/3/15.
 */
public class WeekSignDaoImpl implements WeekSignDao{


    public static void add(String u_account,String week){
        Connection connection=Util.getConnection();
        PreparedStatement prst=null;
        String sql="update sign set "+week+"="+week+"+1"+" where u_account=?";


        try {
            prst=connection.prepareStatement(sql);
            prst.setString(1,u_account);
            prst.executeUpdate();
            System.out.println(prst.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Util.close(connection,prst,null);
        }
    }

    @Override
    public WeekSign getChart(String u_account) {
        WeekSign ws=null;
        Connection connection=Util.getConnection();
        PreparedStatement prst=null;
        ResultSet rs=null;
        String sql="select * from sign where u_account=?";

        try {
            prst=connection.prepareStatement(sql);
            prst.setString(1,u_account);
            rs=prst.executeQuery();
            if(rs.next()){
                ws=new WeekSign();
                ws.setU_account(rs.getString(1));
                ws.setMonday(rs.getInt(2));
                ws.setTuesday(rs.getInt(3));
                ws.setWednesday(rs.getInt(4));
                ws.setThursday(rs.getInt(5));
                ws.setFriday(rs.getInt(6));
                ws.setSaturday(rs.getInt(7));
                ws.setSunday(rs.getInt(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Util.close(connection,prst,rs);
        }

        return ws;

    }



}
