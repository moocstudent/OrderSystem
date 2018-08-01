package com.ykmimi.order.servlet;

import com.ykmimi.order.entity.Customers;
import com.ykmimi.order.service.AuthService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /////* 字符编码设置
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        /////* 获取注册数据
        Customers customer = null;
        String customer_name = request.getParameter("reg_username");
        String customer_password = request.getParameter("reg_password");
        String customer_password2 = request.getParameter("reg_password2");
        if (customer_password.equals(customer_password2)){
            String customer_truename = request.getParameter("reg_truename");
            String customer_address = request.getParameter("reg_address");

            long customer_phone = Long.parseLong(request.getParameter("reg_phone"));
            customer = new Customers(
                    customer_name,customer_password,customer_truename,
                    customer_address,customer_phone
            );
        }
        /////* 上面生成新用户.如果用户不为空则进行注册.
        long cusID = 0;
        if(customer!=null){
            AuthService as = new AuthService();
            cusID =  as.registerNewCustomer(customer);
            if(cusID>1){
                System.out.println("注册成功,用户ID:"+cusID);
                request.setAttribute("cid",cusID);
                HttpSession session = request.getSession(true);
                session.setAttribute("cid",cusID);
                RequestDispatcher rd = request.getRequestDispatcher("/showUser");
                rd.forward(request,response);
            }
        }else{
            response.sendRedirect("/errorJsp/errorReg.jsp");
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
