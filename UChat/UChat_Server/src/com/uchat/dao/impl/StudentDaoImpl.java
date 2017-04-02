package com.uchat.dao.impl;

import com.uchat.dao.StudentDao;
import com.uchat.entity.Student;
import com.uchat.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by starwill on 2017/3/20.
 */
public class StudentDaoImpl implements StudentDao {

    //签到成功后更新student表中每一个成员的信息
    @Override
    public void update(String u_account) {
        Connection conn=Util.getConnection();
        PreparedStatement prst=null;
        String sql="update student set sign_in=1 where student_id=?";
        try{
            prst=conn.prepareStatement(sql);
            prst.setString(1,u_account);
            prst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Util.close(conn,prst,null);
        }

    }

    //获取更新的签到信息
    @Override
    public List<Student> getInfo(String course_id) {
        List<Student> list=new ArrayList<>();
        Connection conn= Util.getConnection();
        PreparedStatement prst=null;
        String sql="select * from student inner join course " +
                "where course_id=? and student.student_id=course.student_id";
        ResultSet rs = null;
        try{
            prst=conn.prepareStatement(sql);
            prst.setString(1,course_id);
            rs=prst.executeQuery();
            while (rs.next()){
                Student student=new Student();
                student.setStudent_id(rs.getString("student_id"));
                student.setNickname(rs.getString("nickname"));
                student.setSign_in(rs.getInt("sign_in"));
                System.out.println(student.getStudent_id()+" : "+student.getSign_in());
                list.add(student);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Util.close(conn,prst,rs);
        }
        return list;
    }


    //重置student表中的签到信息
    @Override
    public void clear(){
        Connection conn=Util.getConnection();
        PreparedStatement prst=null;
        String sql="update student set sign_in=0";
        try {
            prst=conn.prepareStatement(sql);
            prst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Util.close(conn,prst,null);
        }
    }
}
