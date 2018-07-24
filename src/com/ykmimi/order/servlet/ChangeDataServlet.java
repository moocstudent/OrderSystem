package com.ykmimi.order.servlet;

import com.ykmimi.order.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChangeDataServlet")
public class ChangeDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /////*字符编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        /////* 获取用户更新的数据
        String truename = "";
        String address = "";
        String phone = "";
        truename = request.getParameter("change_truename");
        address = request.getParameter("change_address");
        phone = request.getParameter("change_phone");

        AuthService as = null;
        /////*其中有一项不为空的,则进行用户信息更新
        if(! ((truename.length()==0)||(address.length()==0)||(phone.length()==0))){
            as = new AuthService();
        }

        if(!(truename.length()==0)){
            System.out.println("更新用户实名");
            /////* 更改用户实名后set这个新的实名到用户对象,
            /////* 使用session重新设置这个对象
            int changeState = 0;
//            changeState = as.changeTrueName(truename);
            if (changeState==1){
                System.out.println("更新用户实名成功!");
                /////* 将用户实名传送到现在的session中的用户对象 并重新赋值?

            }
        }
        if(!(address.length()==0)){
            System.out.println("更新用户地址");
        }
        if(!(phone.length()==0)){
            System.out.println("更新用户电话");
            long phoneL = Long.parseLong(phone);
            /////*这里不做位数判断,交给JS

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
