<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单管理</title>
</head>
<body>
<!-- action对应的地址必须有一个/开始 -->
<form action="/OrderSystem/test" method="post">
输入订单号查询:<input type="text" name="orderID"> <br>
提交: <input type="submit" name="submit">
</form>
<br>
<%=
request.getRequestURL()
%>
</body>
</html>