package com.ykmimi.order.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ykmimi.order.service.AuthService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		///// * 字符编码设置
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		///// * 获取前端的数据 登陆用户名及密码
		String username = request.getParameter("log_username");
		String password = request.getParameter("log_password");
		///// * 传入Service看是否有这个用户,获取cid --> customer_id
		AuthService as = new AuthService();
		long cid = 0;///// *初始为0即没有该用户,有该用户则返回用户的cid,如果Dao出错则为-1
		cid = as.loginByNameAndPassWord(username, password);
		if (cid > 0) {//如果用户id大于0,则证明登陆成功了
			System.out.println(cid);
			///// *设置customer_id到页面/showUser,其中/showUser是个Servlet-->ShowUserServlet
			///// *ShowUserServlet用于通过cid查询用户信息,展示信息到另一个页面

			/////* 💗其实这里应该是在登陆后就获取了用户实例,并通过Session设置
//			request.setAttribute("cid", cid);

			/////* 第一次获取session时候,使用true,如果没有就新创建一个
			/////* 在其余时间获取这个session时,使用false
			HttpSession session = request.getSession(true);//first session
			//session写入cid
			session.setAttribute("cid",cid);
			/////*加入了 cookie,先写入cookie再跳转到showUser
//			RequestDispatcher rd = request.getRequestDispatcher("/writecookie");
			RequestDispatcher rd = request.getRequestDispatcher("/showUser");
			rd.forward(request, response);
			///// * 正常获取customer_id,跳至含有用户信息的订餐系统页面,含有登陆后功能.
		} else if (cid == -1) {
			System.out.println("Dao层出错,或许是数据库驱动没找到.");
			request.getRequestDispatcher("/errorJsp/errorLog.jsp").forward(request,response);
			///// * Dao出错 , 跳至出错提示页面,再返回首页
		} else if (cid == 0) {
			System.out.println("此用户不存在.");
			response.sendRedirect("/errorJsp/error.jsp");
//			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
//			rd.forward(request, response);
			///// * 进行了DB的查询,但无此用户,跳至提示页面,再返回首页
		} else {
			///// * 未知错误
			response.sendRedirect("/errorJsp/errorMessage.jsp");
			System.out.println("未知错误!");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
