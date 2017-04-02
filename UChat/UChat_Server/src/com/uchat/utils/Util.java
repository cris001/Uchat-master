package com.uchat.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by xuge on 2017/3/6.
 */
public class Util {
    public static Connection getConnection(){
        Connection conn=null;
        //synchronized(Util.class){	//这种方式是确保util的class类是同一个对象
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/uchat?user=root&password=&useUnicode=true&characterEncoding=UTF-8";
            String user="root";
            String pass="root";
            conn=DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //}
        return conn;
    }
    public static void close(Connection cn,Statement st,ResultSet rs){
        try {
            if(cn!=null){
                cn.close();
            }
            if(st!=null){
                st.close();
            }
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
