package com.ykmimi.order.servlet;

import com.ykmimi.order.entity.Customers;
import com.ykmimi.order.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户余额充值
 */
@WebServlet(name = "RechargeServlet")
public class RechargeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ///// * 字符编码设置
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        /////* 充值, 获取模拟传入的input_money
        String in_money = request.getParameter("input_money");
        double money = 0;
        if (!in_money.equals("")){
            money = Double.parseDouble(in_money);
        }else{
            request.setAttribute("rechargeMessage","请输入金币💗");
            request.getRequestDispatcher("/recharge.jsp").forward(request,response);
        }
        HttpSession session = request.getSession(false);
        Customers customer = null;
        customer = (Customers) session.getAttribute("customer");
        //*我想可以通过设置-数作为减钱.不然就直接设置为不可负数
        if (money!=0){
            AuthService as = new AuthService();
            double[] checkStateAndMoney = null;
            checkStateAndMoney = as.rechargeBalance(customer.getCustomer_id(),money);
            if (checkStateAndMoney[0]==1){
                request.setAttribute("rechargeMessage","充值成功!💗");
                /////* session里面放入money不可靠,也不太允许往session里设置一些无关紧要或者至关重要的值.
                /////* 一般用来放入用户的实例或标识.
                request.setAttribute("rechargeMoney",checkStateAndMoney[1]);
                request.getRequestDispatcher("/recharge.jsp").forward(request,response);
            }else if(checkStateAndMoney[0]==-4){
                request.setAttribute("rechargeMessage","充值失败...");
                request.getRequestDispatcher("/recharge.jsp").forward(request,response);
            }else if(checkStateAndMoney[0]==0){
                request.setAttribute("rechargeMessage","请输入+/-钱数,不可为0...");
                request.getRequestDispatcher("/recharge.jsp").forward(request,response);
            }
        }else if(money==0){
            request.setAttribute("rechargeMessage","请输入+/-钱数,不可为0...");
            request.getRequestDispatcher("/recharge.jsp").forward(request,response);
        }else{
            request.setAttribute("rechargeMessage","未知错误💗");
            request.getRequestDispatcher("/recharge.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
