package com.uchat.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uchat.dao.BaseDao;
import com.uchat.entity.Admin;
import com.uchat.utils.Util;

import com.uchat.dao.AdminDao;

/**
 * Created by xuge on 2017/3/7.
 */
public class AdminDaoImpl extends BaseDao implements AdminDao{
    //增加admin
    public boolean add(Admin admin){
        conn=Util.getConnection();
        boolean flag=false;

        String sql="insert into admin(loginname, loginpsw, username, createtime) values(?,?,?,?)";
        Object[] obj={admin.getLoginname(),admin.getLoginpsw(),admin.getUsername(),admin.getCreatetime()};

        if(conn!=null){
            if(update(sql, obj)!=-1){
                flag=true;
            }
            Util.close(conn, ps, rs);
        }
        return flag;
    }
    public boolean delete(int id){
        conn=Util.getConnection();
        boolean flag=false;
        String sql="delete from admin where id=?";
        Object[] obj={id};
        if(conn!=null){
            if(update(sql, obj)!=-1){
                flag=true;
            }
            Util.close(conn, ps, rs);
        }
        return flag;
    }

    public Admin modify(Admin admin, String type){//修改信息
        conn=Util.getConnection();
        /**
         * 修改username
         */
        if("username".equals(type)) {
            String sql = "update admin set username=? where id=?";
            Object[] obj = {admin.getUsername(), admin.getId()};
            if(conn!=null) {
                if (update(sql, obj) != -1) {
                    admin = selectbyid(admin.getId());
                }
            }
            Util.close(conn, ps, rs);
        }
        /**
         * 修改密码
         */
        if("loginchange".equals(type)) {
            String sql = "update admin set loginpsw=? where id=?";
            Object[] obj = {admin.getLoginpsw(), admin.getId()};
            if(conn!=null) {
                if (update(sql, obj) != -1) {
                    admin = selectbyid(admin.getId());
                }
            }
            Util.close(conn, ps, rs);
        }



        return admin;
    }


    public Admin login(String loginname, String loginpsw){
        conn=Util.getConnection();
        String sql="select * from admin where loginname=? and loginpsw=?";
        Admin admin = new Admin();
        Object[] obj={loginname,loginpsw};
        try {
            ResultSet rs=query(sql, obj);
            while(rs.next()){

                int id=rs.getInt(1);
                String username=rs.getString(4);
                String createtime=rs.getString(5);
                admin =new Admin(id, loginname, loginpsw, username, createtime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{//释放资源
            Util.close(conn, ps, rs);
        }
        return admin;
    }

    public List<Admin> select(){//获取集合
        conn=Util.getConnection();
        List<Admin> list=new ArrayList<Admin>();
        String sql="select * from admin";
        Object[] obj={};
        try {
            ResultSet rs=query(sql, obj);//获取结果集
            while(rs.next()){
                int id=rs.getInt(1);
                String loginname=rs.getString(2);
                String loginpsw=rs.getString(3);
                String username=rs.getString(4);
                String createtime=rs.getString(5);
                Admin admin1 =new Admin(id, loginname, loginpsw, username, createtime);
                list.add(admin1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{//释放资源
            Util.close(conn, ps, rs);
        }
        System.out.println(list.size());
        return list;
    }


    public Admin selectbyid(int id) {
        conn = Util.getConnection();
        Admin adminnew = new Admin();
        String sql = "select * from admin where id=" + id + "";
        Object[] obj = {};
        try {
            ResultSet rs = query(sql, obj);//获取结果集
            while (rs.next()) {
                int nid = rs.getInt(1);
                String loginname = rs.getString(2);
                String loginpsw = rs.getString(3);
                String username = rs.getString(4);
                String createtime = rs.getString(5);
                adminnew = new Admin(id, loginname, loginpsw, username, createtime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {//释放资源
            Util.close(conn, ps, rs);
        }
        return adminnew;
    }
}
