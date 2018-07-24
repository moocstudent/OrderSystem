package com.ykmimi.order.servlet;

import com.ykmimi.order.entity.Customers;
import com.ykmimi.order.entity.ShowOrders;
import com.ykmimi.order.service.AuthService;
import com.ykmimi.order.service.OrdersService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
付款Ser
 */
@WebServlet(name = "PaymentServlet")
public class PaymentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ///// * 字符编码设置
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        System.out.println("get in PaymentServlet");

        //* 获取Cookie
        long cid = 0;
        Cookie[] cs = request.getCookies();
        if(cs.length>0){
            for(Cookie c : cs){
                if(c.getName().equals("cidCookie")){
                    //获取Cookie中的customer_id
                    cid = Long.parseLong(c.getValue());
                    break;
                }
            }
        }
        if(cid>0){//如果用户id存在则创建新购物车元组数据并返回long型cart_id
            //查询cid的用户实例
            AuthService as = new AuthService();
            Customers customer = null;
            customer =  as.getCustomersInstanceByID(cid);
            System.out.println(customer);
        }
        /////* 获取订单ID
        long orderID = Long.parseLong(request.getParameter("orderID_in"));
        long cartID = Long.parseLong(request.getParameter("cartID_in"));
        System.out.println(orderID+" "+cartID);
        if(orderID>0){
            System.out.println("orderID>0 OK");
            OrdersService os = new OrdersService();
            int payState = 0;
            payState = os.payForOrder(orderID,cid);
            if (payState==1){
                System.out.println("扣款成功!💗");
                ShowOrders showOrder = null;
                showOrder = os.showAlreadyPaidOrder(orderID,cartID);
                System.out.println(showOrder);
                request.setAttribute("showOrder",showOrder);
                RequestDispatcher rd = request.getRequestDispatcher("/paymentSuccess.jsp");
                rd.forward(request,response);
            }else if(payState==0){
                request.getRequestDispatcher("/paymentFails.jsp").forward(request,response);
            }
        }

        /////* 获取
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
