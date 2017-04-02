package com.uchat.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import com.uchat.dao.ChartDao;
import com.uchat.dao.StudentDao;
import com.uchat.dao.impl.*;
import com.uchat.entity.Admin;
import com.uchat.entity.Student;
import com.uchat.entity.User;
import com.uchat.dao.AdminDao;
import sun.misc.CharacterDecoder;

/**
 * Created by xuge on 2017/3/7.
 */
@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String u_account=request.getParameter("account");
        String u_password=request.getParameter("password");
        String u_nickname=request.getParameter("nickname");
        String r_type=request.getParameter("r_type");
        System.out.println(u_account);

        User user=new User();
        UserDaoImpl userDao=new UserDaoImpl();
        user.setU_account(u_account);
        user.setU_password(u_password);
        user.setU_nickname(u_nickname);


        StudentDao studentDao=new StudentDaoImpl();
        //判断请求类型是否为签到成功
        if("bookin".equals(r_type)){
            String week=getWeek();
            System.out.println(week);
            //更新学生签到表
            studentDao.update(u_account);
            //更新个人表
            WeekSignDaoImpl.add(u_account,week);
            //更新总表
            addChart();


        }


//       判定account是否存在，不存在执行插入操作
        if(!userDao.queryUserByAccount(u_account)){
            System.out.println("test1");
            userDao.insertUser(user);
        }
//        修改用户信息时更新数据库
        String type=request.getParameter("type");
        System.out.println(type);
        if(type!=null){
            String content=request.getParameter(type);
            System.out.println(content);
            userDao.updateUser(u_account,"u_"+type,content);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginname=request.getParameter("loginname");
        String loginpsw=request.getParameter("loginpsw");
        AdminDao adminDao=new AdminDaoImpl();
        if(loginname!=null && loginpsw!=null){
            Admin admin=adminDao.login(loginname,loginpsw);
            if(admin.getLoginpsw()==null){
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println("<script language='javascript'>alert('用户名或密码错误!');history.back();</script>");
                out.close();

            }else{
                request.getSession().setAttribute("admin",admin);//jsp/loginSuccess.jsp
                request.getRequestDispatcher("./admin/index.jsp").forward(request, response);

            }
        }

    }

    private String getWeek(){
        Date date=new Date(System.currentTimeMillis());
        String weeks[]={"sunday_count","monday_count","tuesday_count","wednesday_count",
                "thursday_count","friday_count","saturday_count"};
        Calendar cl=Calendar.getInstance();
        cl.setTime(date);
        int week_index=cl.get(Calendar.DAY_OF_WEEK)-1;
        if(week_index<0){
            week_index=0;
        }
        return weeks[week_index];
    }
    private void addChart(){
        ChartDao chartDao=new ChartDaoImpl();
        Calendar a=Calendar.getInstance();
        String date =null;
        int month=a.get(Calendar.MONTH);
        month=month+1;
        if(a.get(Calendar.MONTH)<9){
            date = a.get(Calendar.YEAR)+"-0"+month+"-"+a.get(Calendar.DATE);
        }else{
            date = a.get(Calendar.YEAR)+"-"+month+"-"+a.get(Calendar.DATE);
        }

        System.out.println("日期："+date);
        int index = chartDao.selectdate(date);
        boolean flag = chartDao.add(date, index);
        if(flag){
            System.out.println(flag);
//            request.getRequestDispatcher("./admin/index.jsp").forward(request, response);
        }else{
            System.out.println(flag);
//            request.getRequestDispatcher("./admin/login.jsp").forward(request, response);
        }
    }

}
