package com.uchat.servlet;

import com.uchat.dao.WeekSignDao;
import com.uchat.dao.impl.WeekSignDaoImpl;
import com.uchat.entity.WeekSign;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/3/15.
 */
@WebServlet(name = "WeekSignServlet",urlPatterns = "/WeekSignServlet")
public class WeekSignServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String u_account=request.getParameter("u_account");
        WeekSign weekSign;
        WeekSignDao wsd=new WeekSignDaoImpl();
        weekSign=wsd.getChart(u_account);

        System.out.println(u_account);

        PrintWriter out=response.getWriter();
        if(weekSign!=null){
            JSONObject object=new JSONObject();
            object.put("1",weekSign.getMonday());
            object.put("2",weekSign.getTuesday());
            object.put("3",weekSign.getWednesday());
            object.put("4",weekSign.getThursday());
            object.put("5",weekSign.getFriday());
            object.put("6",weekSign.getSaturday());
            object.put("7",weekSign.getSunday());

            System.out.println(object+"");

            out.println(object+"");
        }
    }
}
