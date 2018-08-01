<%--
  Created by IntelliJ IDEA.
  User: SeeClanUkyo
  Date: 2018/07/22
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>支付成功</title>
</head>
<body>
<p>支付成功💗</p>
1.支付成功展示出单详情 待格式完整化 <br>
2.展示用户扣钱

<br>
<hr>
<%--支付成功展示出单详情 待格式完整--%>
<%if(request.getAttribute("showOrder")!=null){%>
  <%=request.getAttribute("showOrder")%>
<%}%>
<hr>
<br>
<a href="/index.jsp">返回首页</a>
<%--展示当前路径--%>
<%=request.getRequestURL()%>
</body>
</html>
<%--在使用<%的时候如果是一个实例或者数值型数据则结尾加;号--%>
<%--<% request.getAttribute("showOrder") ;%> --%>
<%--<% if(3>2){}%>--%>