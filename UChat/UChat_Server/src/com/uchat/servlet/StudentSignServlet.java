package com.uchat.servlet;

import com.uchat.dao.StudentDao;
import com.uchat.dao.impl.StudentDaoImpl;
import com.uchat.entity.Student;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by starwill on 2017/3/20.
 */
@WebServlet(name = "StudentSignServlet",urlPatterns = "/StudentSignServlet")
public class StudentSignServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String course_id=request.getParameter("course_id");
        String type=request.getParameter("type");
        PrintWriter out=response.getWriter();
        StudentDao studentDao=new StudentDaoImpl();

        System.out.println("------type:"+type);
        if("clear".equals(type)){
            studentDao.clear();
        }
        else if("refresh".equals(type)){
            List<Student> list=new ArrayList<>();
            list=studentDao.getInfo(course_id);
            JSONObject object=new JSONObject();
            if(list!=null){
                for(int i=0;i<list.size();i++){
                    object.put(i+"",list.get(i));
                }
            }

            out.print(object.toString());
        }


    }
}
