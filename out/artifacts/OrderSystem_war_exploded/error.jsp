<%--
  Created by IntelliJ IDEA.
  User: SeeClanUkyo
  Date: 2018/07/18
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户不存在</title>
</head>
<body>
<p>当前用户不存在</p>

<a href="reg.jsp">注册一个</a><br>
<a href="index.jsp">返回首页</a><br>
<br>
<%=
request.getRequestURL()
%>
</body>
</html>
