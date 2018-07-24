package com.ykmimi.order.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReadCookieServlet")
public class ReadCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ///// * 字符编码设置
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");


        long cid = 0;
        /////* 获取cookie
        Cookie[] cs = request.getCookies();

        if(cs.length>0){
            for(Cookie c : cs){
                //获取Cookie的name,key
                String name = c.getName();
                System.out.println(name);
                request.setAttribute("name",name);
                if(name.equals("cidCookie")){
                    cid = Long.parseLong(c.getValue());
                    request.setAttribute("cidC",cid);
                    break;
                }
//                String value = c.getValue();
//                System.out.println(value);
//                request.setAttribute("value",value);
            }
        }//End if

        RequestDispatcher rd = request.getRequestDispatcher("/userInfo.jsp");
        rd.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
