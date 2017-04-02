package com.uchat.dao.impl;

import com.uchat.dao.BaseDao;
import com.uchat.utils.Util;
import com.uchat.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;



import com.uchat.dao.UserDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuge on 2017/3/9.
 */
public class UserDaoImpl extends BaseDao implements UserDao{

//    public boolean add(User User){
//        conn=Util.getConnection();
//        boolean flag=false;
//
//        String sql="insert into User(name, passwd, email, icon, sign, birth, mobile, gender,type) values(?,?,?,?,?,?,?,?,?)";
//        Object[] obj={User.getName(), User.getPasswd(), User.getEmail(), User.getIcon(),User.getSign(), User.getBirth(), User.getMobile(), User.getGender(), User.getType()};
//
//        if(conn!=null){
//            if(update(sql, obj)!=-1){
//                flag=true;
//            }
//            Util.close(conn, ps, rs);
//        }
//        return flag;
//    }


//删除
    public boolean delete(String u_account){
        conn=Util.getConnection();
        boolean flag=false;
        String sql="delete from User where u_account=?";
        Object[] obj={u_account};
        if(conn!=null){
            if(update(sql, obj)!=-1){
                flag=true;
            }
            Util.close(conn, ps, rs);
        }
        return flag;
    }

//服务器端修改权限
    public boolean modifytype(String u_account, int u_permission){//修改权限
        conn=Util.getConnection();

            String sql = "update User set u_permission=? where u_account=?";
            Object[] obj = {u_permission, u_account};
            boolean flag = false;
            if(conn!=null) {
                if (update(sql, obj) != -1) {
                    flag=true;
                }
            }
            Util.close(conn, ps, rs);

        return flag;
    }

//用于服务器端的获取数据
    public List<User> select(){//获取集合
        conn=Util.getConnection();
        List<User> list=new ArrayList<User>();
        String sql="select * from User";
        Object[] obj={};
        try {
            ResultSet rs=query(sql, obj);//获取结果集
            while(rs.next()){
                String u_account =rs.getString(1);
                String u_nickname=rs.getString(2);
                String u_password=rs.getString(3);
                String u_email=rs.getString(4);
                String u_signature=rs.getString(5);
                String u_birthday=rs.getString(6);
                String u_phone=rs.getString(7);
                String u_gender=rs.getString(8);
                int u_permission=rs.getInt(9);
                String u_identification=rs.getString(10);
                User user1 =new User( u_account, u_nickname, u_password, u_email, u_signature, u_birthday, u_phone, u_gender, u_permission, u_identification);
                list.add(user1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{//释放资源
            Util.close(conn, ps, rs);
        }
        System.out.println(list.size());
        return list;
    }

//查询用户是否存在
    public boolean queryUserByAccount(String u_account) {
        conn = Util.getConnection();
        String sql = "select * from User where u_account=?";
        boolean flag = false;
        Object[] obj={u_account};
        try {
            ResultSet rs = query(sql, obj);//获取结果集
            while (rs.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {//释放资源
            Util.close(conn, ps, rs);
        }
        return flag;
    }


//查询用户权限
    public User queryPermission(String u_account){
        User user=null;
        Connection conn=Util.getConnection();
        String sql="select u_permission from User where u_account= ?";
        PreparedStatement prst= null;
        ResultSet rs=null;
        try {
            prst=conn.prepareStatement(sql);
            prst.setString(1,u_account);
            rs=prst.executeQuery();
            if(rs.next()){
                user=new User();
                user.setU_permission(rs.getInt("u_permission"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            Util.close(conn,prst,rs);
        }
        return user;
    }

    //注册新新用户
    public void insertUser(User user){
        conn = Util.getConnection();
        PreparedStatement prst=null;
        String sql="insert into User(u_account,u_password,u_nickname,u_permission) values(?,?,?,1)";
        try {
            prst=conn.prepareStatement(sql);
            prst.setString(1,user.getU_account());
            prst.setString(2,user.getU_password());
            prst.setString(3,user.getU_nickname());
            prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            Util.close(conn,prst,null);
        }

    }

    //更新用户信息
    public void updateUser(String account,String type,String content){
        conn = Util.getConnection();
        PreparedStatement prst=null;
        String sql="update User set "+type+"=? where u_account=?";
        try {
            prst=conn.prepareStatement(sql);

            prst.setString(1,content);
            prst.setString(2,account);
            System.out.println(prst.toString());
            prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            Util.close(conn,prst,null);
        }
    }
}
