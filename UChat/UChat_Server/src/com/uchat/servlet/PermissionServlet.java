package com.uchat.servlet;


import com.uchat.dao.impl.UserDaoImpl;
import com.uchat.entity.User;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by starwill on 2017/3/13.
 */
@WebServlet(name = "PermissionServlet",urlPatterns = "/PermissionServlet")
public class PermissionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

//接受客户端请求
        String u_account=request.getParameter("u_account");
        System.out.println(u_account);
        String u_identification=request.getParameter("u_identification");


        PrintWriter out=response.getWriter();

//        查询权限
        User user;
        String u_permission;
        UserDaoImpl userDao=new UserDaoImpl();
        if(userDao.queryUserByAccount(u_account)){
            user=userDao.queryPermission(u_account);
            if(user.getU_permission()==0){
                u_permission="班主任";
            }else if(user.getU_permission()==1){
                u_permission="学生";
            }else {
                u_permission="教师";
            }



            System.out.println(u_permission);

//绑定为json类型传递给客户端
            JSONObject json=new JSONObject();
            json.put("u_permission",u_permission);
            out.println(json+"");


            System.out.println(json+"");
        }
    }
}
