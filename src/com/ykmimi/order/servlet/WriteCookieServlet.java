package com.ykmimi.order.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WriteCookieServlet")
public class WriteCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ///// * 字符编码设置
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取Attribute里的cid
        long cid = 0;
        cid = (long)request.getAttribute("cid");
        if(cid>0){
//            request.setAttribute("cid",cid);
            Cookie cookie = new Cookie("cidCookie",String.valueOf(cid));
            cookie.setPath(request.getContextPath());//设置cookie作用域为当前项目
            //Cookie有效期,该值大于0,表示将Cookie存放到客户端的硬盘
            //该值小于0,比与不设置相同,会将Cookie存放到浏览器的缓存
            //该值等于0,表示Cookie一生成,马上失效.
            cookie.setMaxAge(60*60*24);//设置cookie有效期:生命周期1天
            System.out.println("--------写入cookie----并转发跳转---------");
            response.addCookie(cookie);
            request.getRequestDispatcher("/showUser").forward(request,response);
        } else if (cid == -1) {
            System.out.println("Dao层出错,或许是数据库驱动没找到.");
            ///// * Dao出错 , 跳至出错提示页面,再返回首页
        } else if (cid == 0) {
            System.out.println("此用户不存在.");
            response.sendRedirect("/error.jsp");
//			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
//			rd.forward(request, response);
            ///// * 进行了DB的查询,但无此用户,跳至提示页面,再返回首页
        } else {
            ///// * 未知错误
            response.sendRedirect("/errorMessage.jsp");
            System.out.println("未知错误!");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
