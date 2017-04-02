package com.uchat.servlet;

import com.uchat.dao.AdminDao;
import com.uchat.dao.impl.AdminDaoImpl;
import com.uchat.entity.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by xuge on 2017/3/7.
 */
@WebServlet(name = "AdminServlet")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type=request.getParameter("type");
        AdminDao adminDao=new AdminDaoImpl();

        /**
         * 添加
         */
        if("add".equals(type) ){
            String loginname=request.getParameter("loginname");
            String loginpsw=request.getParameter("loginpsw");
            String username=request.getParameter("username");
            String createtime=request.getParameter("createtime");
            Admin admin = new Admin(loginname,loginpsw,username,createtime);
            boolean flage = adminDao.add(admin);
            if(flage){
                List<Admin> adminList=adminDao.select();
                request.getSession().setAttribute("adminList",adminList);//jsp/loginSuccess.jsp
                request.getRequestDispatcher("./admin/staff.jsp").forward(request, response);
            }
        }

        /**
         * 登陆处修改密码
         */
        if("update_login".equals(type) ){
            System.out.println("aaaaaaa");
            String loginname=request.getParameter("loginname");
            String loginpsw=request.getParameter("loginpsw");
            Admin admin=adminDao.login(loginname,loginpsw);
            String newloginpsw=request.getParameter("newloginpsw");
            if(admin.getLoginpsw()!=null){
                System.out.println("id:"+admin.getId());
                admin=new Admin(admin.getId(),newloginpsw);
                admin=adminDao.modify(admin,"loginchange");
                if(admin.getLoginpsw()!=null){
                    request.getSession().setAttribute("admin",admin);
                    request.getRequestDispatcher("./admin/login.jsp").forward(request, response);
                }else{
                    request.getRequestDispatcher("./admin/login.jsp").forward(request, response);
                }
            }else{
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println("<script language='javascript'>alert('原密码或用户名错误!');history.back();</script>");
                out.close();

            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type=request.getParameter("type");
        AdminDao adminDao=new AdminDaoImpl();

        /**
         * 查询员工列表
         */
        if("select".equals(type) ){
            List<Admin> adminList=adminDao.select();
            request.getSession().setAttribute("adminList",adminList);//jsp/loginSuccess.jsp
            request.getRequestDispatcher("./admin/staff.jsp").forward(request, response);
        }

        if("select_update".equals(type) ){
            String sid=request.getParameter("id");
            int id=Integer.valueOf(sid);
            Admin admin=adminDao.selectbyid(id);
            request.getSession().setAttribute("admin",admin);//jsp/loginSuccess.jsp
            request.getRequestDispatcher("./admin/updatestaff.jsp").forward(request, response);
        }

        if("update".equals(type) ){
            String sid=request.getParameter("id");
            int id=Integer.valueOf(sid);
            String username=request.getParameter("username");
            String loginname=request.getParameter("loginname");
            Admin admin=new Admin(id, loginname, username);
            admin= adminDao.modify(admin, "username");
            if(admin.getLoginpsw()!=null){
                List<Admin> adminList=adminDao.select();
                request.getSession().setAttribute("adminList",adminList);//jsp/loginSuccess.jsp
                request.getRequestDispatcher("./admin/staff.jsp").forward(request, response);
            }
        }

        if("delete".equals(type) ){
            String sid=request.getParameter("id");
            int id=Integer.valueOf(sid);
            boolean flage= adminDao.delete(id);
            if(flage){
                List<Admin> adminList=adminDao.select();
                request.getSession().setAttribute("adminList",adminList);//jsp/loginSuccess.jsp
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println("<script language='javascript'>alert('删除成功!');history.back();</script>");
                out.close();
                // request.getRequestDispatcher("./admin/staff.jsp").forward(request, response);
            }
        }
    }
}
