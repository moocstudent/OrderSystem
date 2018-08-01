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

/**
 * Servlet implementation class ShowUserServlet
 */
@WebServlet("/ShowUserServlet")
public class ShowUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ///// * 字符编码设置
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        /////* requestURL
        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURL);
        String clientIP = request.getRemoteHost();
        System.out.println(clientIP);




        ///// * 获取/login传送过来的long类型值 cid (customer_id)
        HttpSession session = request.getSession(false);
        long cid = 0;
        cid  = (long)session.getAttribute("cid");
        System.out.println(cid);
//        cid =Long.parseLong(cidStr);
        /////* 获取用户id后进行判定,如果不为0的话,将其再进行查询,返回用户的整个实例,再次进行传值
        /////* 并在最终用户登陆后的显示界面上显示用户信息及登陆后的服务信息.
        if (cid > 0) {


            AuthService as = new AuthService();
            /////* 如果这个id比较符合id的规则 即 大于 0, 那么用它去数据库检索这个id的归属者的实例
            Customers customer = null;
            customer = as.getCustomersInstanceByID(cid);
            request.setAttribute("customerIns",customer);
            //设置用户实例到session
            session.setAttribute("customer",customer);

            System.out.println("AuthService获取的用户实例:"+customer);
            //设置customer实例到request

//            session.setMaxInactiveInterval(60*30);//设置Session有效的生命周期
            //跳转
//            RequestDispatcher rd = request.getRequestDispatcher("/readcookie");
            RequestDispatcher rd = request.getRequestDispatcher("/userInfo.jsp");
            rd.forward(request, response);


//            response.sendRedirect("/userInfo.jsp?cid="+cid+"&cname="+customer.getCustomer_name());
        } else if (cid == 0) {
            RequestDispatcher rd = request.getRequestDispatcher("/errorJsp/error.jsp");
            rd.forward(request, response);
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
