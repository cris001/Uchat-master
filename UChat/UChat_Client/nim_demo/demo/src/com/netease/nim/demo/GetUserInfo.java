package com.netease.nim.demo;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求与获取用户信息
 *
 *
 * Created by Administrator on 2017/3/8.
 */

public class GetUserInfo {
    private static String url="http://10.6.12.171:8080/LoginServlet";
    private static String url2="http://10.6.12.171:8080/PermissionServlet";
    public boolean SendDataToServer(HashMap<String,String> map) throws IOException{
        StringBuffer sb=new StringBuffer(url);

        if(url!=null&&map!=null){
            sb.append("?");
            for(Map.Entry<String,String> entry:map.entrySet()){
                sb.append(entry.getKey()+"="+entry.getValue())
                        .append("&");
            }
            sb.charAt(sb.length()-1);
        }

//        byte[] data=sb.toString().getBytes();


            HttpURLConnection urlConnection= (HttpURLConnection) new URL(sb.toString()).openConnection();
            urlConnection.setConnectTimeout(5000);
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);//打开服务器的输入流
            urlConnection.connect();

            //post方式传输
//            OutputStream outputStream= urlConnection.getOutputStream();
//            outputStream.write(data);//输出流传送数据
//            outputStream.flush();
//            outputStream.close();
        if(urlConnection==null){
            Log.i("--------","-----没有链接----");
            return false;
        }

        if(urlConnection.getResponseCode()==200){
            return  true;
        }else {
            Log.i("--------","-----服务器未响应----");
            return false;
        }

    }


    //获取用户权限
    public String GetLimitFromLocalServer(HashMap<String,String> map)throws IOException{
        String limit="student";
        StringBuffer buffer=new StringBuffer(url2);
        if(url!=null&&map!=null){
            buffer.append("?");
            for(Map.Entry<String,String> entry:map.entrySet()){
             buffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            buffer.charAt(buffer.length()-1);
        }

        //请求与服务器的链接
        HttpURLConnection urlConnection=(HttpURLConnection)new URL(buffer.toString()).openConnection();
        urlConnection.setConnectTimeout(5000);
        urlConnection.setRequestProperty("connection","Keep-Alive");
        urlConnection.setRequestMethod("GET");

        //获取数据
        if(urlConnection.getResponseCode()==200){
            Log.i("------------","Connection successful!");
            limit=urlConnection.getHeaderField("u_permission");
        }
        return limit;

    }
}
