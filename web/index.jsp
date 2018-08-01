<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>魔偶小餐馆</title>
    <%--<%@ include file="bootstrap.jsp"%>--%>
</head>
<body>
<h2>魔偶小餐馆</h2>
<p>	请登陆 </p>
<form action="/login" method="post">
用户名: <input type="text" name="log_username"> <br>
密码: <input type="password" name="log_password"><br>
<input type="submit" value="提交">

</form>

<a href="/reg.jsp">无账号请注册</a>
<br>
<%--展示当前路径--%>
<%=request.getRequestURL()%>
<br>

</body>
</html>