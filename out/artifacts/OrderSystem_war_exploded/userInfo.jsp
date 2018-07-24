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
<% Customers customer = (Customers) request.getAttribute("customerInstance"); %>
<% if (customer!=null){%>
<%=customer.getCustomer_truename()%>
<br>
<%}%>
用户ID:
<%= request.getAttribute("cid")%>

<br>
Cookie: <br>
<%= request.getAttribute("name")%>
<%= request.getAttribute("cidC")%>

<del><a href="/foods">查看所有商品</a></del> <br>
<form action="/foods" method="post">
    <input type="hidden" name="cid" value="<%= request.getAttribute("cidC")%>">
    <input type="submit" value="订餐">
</form>
<br>
<%--展示当前路径--%>
<%=request.getRequestURL()%>

</body>
</html>