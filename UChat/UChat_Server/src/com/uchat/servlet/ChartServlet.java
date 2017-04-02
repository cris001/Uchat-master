package com.uchat.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Calendar;

import com.uchat.dao.ChartDao;
import com.uchat.dao.impl.ChartDaoImpl;
import com.uchat.entity.Chart;

/**
 * Created by xuge on 2017/3/13.
 */
@WebServlet(name = "ChartServlet")
public class ChartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type=request.getParameter("type");
        ChartDao chartDao=new ChartDaoImpl();

        /**
         * 日报表
         */
        if("selectday".equals(type) ){
            Calendar a=Calendar.getInstance();
            System.out.println(a.get(Calendar.YEAR));//得到年
//            System.out.println(a.get(Calendar.MONTH)+1);//由于月份是从0开始的所以加1
//            System.out.println(a.get(Calendar.DATE));
//            if(a.get(Calendar.MONTH)+1<10){
//                date=a.get(Calendar.YEAR)+"-0"+(a.get(Calendar.MONTH)+1)+"-";
//            }else{
//                date=a.get(Calendar.YEAR)+"-"+(a.get(Calendar.MONTH)+1)+"-";
//            }

            List<Chart> chartJanList = chartDao.selectforday(a.get(Calendar.YEAR)+"-01");
            List<Chart> chartFebList = chartDao.selectforday(a.get(Calendar.YEAR)+"-02");
            List<Chart> chartMarList = chartDao.selectforday(a.get(Calendar.YEAR)+"-03");
            List<Chart> chartAprList = chartDao.selectforday(a.get(Calendar.YEAR)+"-04");
            List<Chart> chartMayList = chartDao.selectforday(a.get(Calendar.YEAR)+"-05");
            List<Chart> chartJuneList = chartDao.selectforday(a.get(Calendar.YEAR)+"-06");
            List<Chart> chartJulyList = chartDao.selectforday(a.get(Calendar.YEAR)+"-07");
            List<Chart> chartAugList = chartDao.selectforday(a.get(Calendar.YEAR)+"-08");
            List<Chart> chartSeptList = chartDao.selectforday(a.get(Calendar.YEAR)+"-09");
            List<Chart> chartOctList = chartDao.selectforday(a.get(Calendar.YEAR)+"-10");
            List<Chart> chartNovList = chartDao.selectforday(a.get(Calendar.YEAR)+"-11");
            List<Chart> chartDecList = chartDao.selectforday(a.get(Calendar.YEAR)+"-12");
//            if(a.get(Calendar.MONTH)+1==1){
//                date=(a.get(Calendar.YEAR)-1)+"-12-";
//            }else if(a.get(Calendar.MONTH)<10){
//                date=a.get(Calendar.YEAR)+"-0"+(a.get(Calendar.MONTH))+"-";
//            }else {
//                date=a.get(Calendar.YEAR)+"-"+(a.get(Calendar.MONTH))+"-";
//            }
//            System.out.println("日期："+date);
//            List<Chart> chartLastList = chartDao.selectforday(date);
            List<Chart> chartList = chartDao.select();


            request.getSession().setAttribute("chartJanList",chartJanList);//jsp/loginSuccess.jsp
            request.getSession().setAttribute("chartFebList",chartFebList);//jsp/loginSuccess.jsp
            request.getSession().setAttribute("chartMarList",chartMarList);//jsp/loginSuccess.jsp
            request.getSession().setAttribute("chartAprList",chartAprList);//jsp/loginSuccess.jsp
            request.getSession().setAttribute("chartMayList",chartMayList);//jsp/loginSuccess.jsp
            request.getSession().setAttribute("chartJuneList",chartJuneList);//jsp/loginSuccess.jsp
            request.getSession().setAttribute("chartJulyList",chartJulyList);//jsp/loginSuccess.jsp
            request.getSession().setAttribute("chartAugList",chartAugList);//jsp/loginSuccess.jsp
            request.getSession().setAttribute("chartSeptList",chartSeptList);//jsp/loginSuccess.jsp
            request.getSession().setAttribute("chartOctList",chartOctList);//jsp/loginSuccess.jsp
            request.getSession().setAttribute("chartNovList",chartNovList);//jsp/loginSuccess.jsp
            request.getSession().setAttribute("chartDecList",chartDecList);//jsp/loginSuccess.jsp
            request.getSession().setAttribute("chartList",chartList);//jsp/loginSuccess.jsp
            request.getRequestDispatcher("./admin/daychart.jsp").forward(request, response);
        }

        /**
         * 签到
         */
        if("add".equals(type) ){
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
                request.getRequestDispatcher("./admin/index.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("./admin/login.jsp").forward(request, response);
            }
        }

    }
}
