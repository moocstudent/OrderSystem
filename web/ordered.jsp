<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: SeeClanUkyo
  Date: 2018/07/23
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>已下单</title>
</head>
<body>
<p>已下单未支付需要支付的页面</p>

<% if (request.getAttribute("totalPrice") != null && (double) request.getAttribute("totalPrice") > 0.0) {%>
<% if (request.getAttribute("foodIDList") != null && (request.getAttribute("foodNumbersList") != null)) {%>
<%
    ArrayList<Long> foodIDList = (ArrayList<Long>) request.getAttribute("foodIDList");
    ArrayList<Integer> foodNumbersList = (ArrayList<Integer>) request.getAttribute("foodNumbersList");
%>
<% if (request.getAttribute("orderID") != null) {%>
订单号:<%=request.getAttribute("orderID")%> <br>
订单内容: <br>
<%}%>
<% for (int i = 0; i < foodIDList.size(); i++) {%>
套餐ID: <%=foodIDList.get(i)%> 数量: <%=foodNumbersList.get(i)%>
金额: <%=request.getAttribute("price_".concat(foodIDList.get(i) + ""))%>
<br>
<%}%>
<h2>
    <mark>订单总金额:<%=request.getAttribute("totalPrice")%>
    </mark>
</h2>
<%}%>
<form action="/pay" method="post">
    <input type="hidden" name="orderID_in" value="<%=request.getAttribute("orderID")%>">
    <input type="hidden" name="cartID_in" value="<%=request.getAttribute("cartID")%>">
    <input type="submit" value="付款">
</form>
<%}%>





<p>1.区分此页面和用户查看未支付订单的不同,此页面直接连接当前选择的套餐并提示付款.</p>
<p>2.查看的未支付订单,则可采取用户点击这个订单选项后(如href的订单号)再可以进行付款操作.</p>

<%=request.getRequestURL()%>
<br>
<a href="/index.jsp">返回首页💗</a>
</body>
</html>
