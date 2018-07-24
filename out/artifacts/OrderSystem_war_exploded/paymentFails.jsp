<%--
  Created by IntelliJ IDEA.
  User: SeeClanUkyo
  Date: 2018/07/22
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>支付失败</title>
</head>
<body>
<p>重复提交订单,或余额不足请充值💗</p>
<a href="/index.jsp">返回首页</a>
<a href="/recharge.jsp">充值</a>

1.待用户充值Servlet和页面
<%--展示当前路径--%>
<%=request.getRequestURL()%>
</body>
</html>
