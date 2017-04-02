package com.uchat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by xuge on 2017/3/7.
 */
public class BaseDao {
    public Connection conn;
    public PreparedStatement ps;
    public ResultSet rs;
    public Statement st;
    //增删改，需不需要结果集--查啊
    public int update(String sql,Object[] obj){
        int flag=0;
        try {
            ps=conn.prepareStatement(sql);
            for(int i=0;i<obj.length;i++){
                ps.setObject(i+1, obj[i]);
            }
            flag=ps.executeUpdate();
        } catch (SQLException e) {
            flag=-1;
            e.printStackTrace();
        }
        return flag;
    }
    //查询
    public ResultSet query(String sql,Object[] obj){
        try {
            ps=conn.prepareStatement(sql);
            for(int i=0;i<obj.length;i++){
                ps.setObject(i+1, obj[i]);
            }
            rs=ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    public int update(String sql){
        int flag=0;
        try {
            st=conn.createStatement();
            flag=st.executeUpdate(sql);
        } catch (SQLException e) {
            flag=-1;
            e.printStackTrace();
        }
        return flag;
    }
    public ResultSet query(String sql){
        try {
            st=conn.prepareStatement(sql);
            rs=st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
