package com.ykmimi.order.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class TestServlet
 */

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("hello world");
		String uri = req.getContextPath();
		String method = req.getMethod();
		String queryStr = req.getQueryString();//请求中?拼接的内容
		String servletPath = req.getServletPath();//servlet的url-pattern
		String url = req.getRequestURL().toString();//url请求路径
		System.out.println("uri = " + uri);
		System.out.println("method = " + method);
		System.out.println("queryStr = " + queryStr);
		System.out.println("servletPath = " + servletPath);
		System.out.println("url = " + url);
		
		
//		String responseType = resp.getContentType();
//		String reqType = req.getContentType();
//		System.out.println("uri:"+uri+"\nmethod:"+method+"\n");
//		System.out.println("reqType:"+reqType);
//		System.out.println("responseType:"+responseType);
	}
   /* *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}*/

}
