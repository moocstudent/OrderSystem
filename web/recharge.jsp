<%@ page import="com.ykmimi.order.entity.Customers" %><%--
  Created by IntelliJ IDEA.
  User: SeeClanUkyo
  Date: 2018/07/23
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>充值余额</title>
</head>
<body>
<%=request.getRequestURL()%> <br>
显示当前余额: <br>

<% if (session.getAttribute("rechargeMoney")!=null){%>
    <mark><%=session.getAttribute("rechargeMoney")%>元</mark>
<%}else{
    if(session.getAttribute("customer")!=null){%>
        <%Customers customer = (Customers)session.getAttribute("customer");%>
        <mark><%=customer.getCustomer_balance()%>元</mark>
    <%}%>
<%}%>

<%--提示信息--%>
<% if(request.getAttribute("rechargeMessage")!=null){%>
    <%=request.getAttribute("rechargeMessage")%>
<%}%>

<p>余额充值</p> <br>
小数点后最多5位 <br>
<form action="/recharge" method="post">
    输入充值金额: <input type="text" name="input_money"><br>
    <input type="submit" value="点击充值"> <br>
</form>

<br>
<a href="/index.jsp">返回首页</a>
<br>
<hr>
1.刷新页面就提交上次订单充值金额?应使用JS加强监管
2.充值后空着充值栏提交,会返回上回的金钱状态(改用sendRedirect)
</body>
</html>
