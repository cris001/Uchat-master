package com.uchat.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.uchat.dao.UserDao;
import com.uchat.dao.impl.UserDaoImpl;
import com.uchat.entity.User;



/**
 * Created by xuge on 2017/3/9.
 */
@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type=request.getParameter("type");
        UserDao userDao=new UserDaoImpl();
        /**
         * 查询用户列表
         */
        if("select".equals(type) ){
            List<User> userList=userDao.select();
            request.getSession().setAttribute("userList",userList);//jsp/loginSuccess.jsp
            request.getRequestDispatcher("./admin/user.jsp").forward(request, response);
        }

        if("modifytype".equals(type) ){
            String permission=request.getParameter("changetype");
            int u_permission=Integer.valueOf(permission);
            String u_account=request.getParameter("id");
            System.out.println("type:"+u_permission);
            boolean flag=userDao.modifytype(u_account, u_permission);
            List<User> userList=userDao.select();
            request.getSession().setAttribute("userList",userList);//jsp/loginSuccess.jsp
            request.getRequestDispatcher("./admin/user.jsp").forward(request, response);
        }

        if("delete".equals(type) ){
            String u_account=request.getParameter("id");
            boolean flage= userDao.delete(u_account);
                List<User> userList=userDao.select();
                request.getSession().setAttribute("userList",userList);//jsp/loginSuccess.jsp
                request.getRequestDispatcher("./admin/user.jsp").forward(request, response);
        }
    }
}
