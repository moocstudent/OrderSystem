package com.ykmimi.order.servlet;

import com.ykmimi.order.entity.Customers;
import com.ykmimi.order.entity.Foods;
import com.ykmimi.order.service.FoodsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "FoodsServlet")
public class FoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ///// * 字符编码设置
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        System.out.println("get in FoodsServlet");
        FoodsService fs = new FoodsService();
        ArrayList<Foods> foodsList = fs.getAllFoodsInfo();
        request.setAttribute("foodslist",foodsList);

//        //获取用户实例
//        Customers customer = null;
//        HttpSession session = request.getSession(false);
//        customer = (Customers)session.getAttribute("customer");
//        System.out.println("FoodsServlet:"+customer);

        RequestDispatcher rd = request.getRequestDispatcher("/foods.jsp");
        rd.forward(request,response);//加油 坚持就是胜利

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
        //*如果当这里不设置doPost...当用户在用户界面跳转点餐后,将无法进入该Servlet
    }
}
