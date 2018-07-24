<%@ page import="com.ykmimi.order.entity.Customers" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆成功,(＾Ｕ＾)ノ~ＹＯ</title>
</head>
<body>
<p>用户信息界面</p>
<% Customers customer = (Customers) session.getAttribute("customer"); %>
<% if (customer!=null){%>
欢迎您:<%=customer.getCustomer_truename()%>

<br>
<%}%>
用户ID:
<%= session.getAttribute("cid")%>

<br>


<a href="/foods">订餐</a> <br>

<!--下面为已废弃的代码-->
<%--<form action="/foods" method="post">--%>
    <%--<input type="hidden" name="cid" value="<%= request.getAttribute("cid")%>">--%>
    <%--<input type="submit" value="订餐">--%>
<%--</form>--%>
<br>
<%--展示当前路径--%>
<%=request.getRequestURL()%>

<hr>
<% if(session.getAttribute("customer")!=null){%>
<%Customers customerIns = (Customers)session.getAttribute("customer");%>
用户实名:<%=customerIns.getCustomer_truename()%> <br>
用户地址:<%=customerIns.getCustomer_address()%> <br>
用户电话:<%=customerIns.getCustomer_phone()%> <br>
<%}%>

<hr>
修改信息:如果不修改则不输入
<form action="/changeData" method="post">
    修改实名信息: <input type="text" name="change_truename">
    修改收货地址: <input type="text" name="change_address">
    修改电话: <input type="text" name="change_phone"> <br>
    <input type="submit" value="更新资料">
</form><br>


<a href="/recharge.jsp">查看/充值余额</a>

<br>
<hr>
1.未实现几个input的输入框功能 <br>
2.手机位数判断等,使用JS去判定 <br>

<hr>
1.已改善的bug:使用a标签跳转/foods路径(servlet)正常了.在FoodsServlet(42行)加入了doPost(request,response);
(原因是a标签跳转的时候如果没有42行的设置,将会只处理post请求,而不处理get请求)
</body>
</html>