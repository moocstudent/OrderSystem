<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ykmimi.order.entity.Foods" %>
<%@ page import="com.ykmimi.order.entity.Customers" %><%--
  Created by IntelliJ IDEA.
  User: SeeClanUkyo
  Date: 2018/07/19
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>食品列表</title>
    <%@include file="bootstrap.jsp" %>
</head>
<body>
<%if(session.getAttribute("customer")!=null){%>
   <%Customers customer = (Customers)session.getAttribute("customer");%>
您好,<%=customer.getCustomer_truename()%>,请选取您要点的套餐及数量!💗
<%}%>
<p>食品列表</p>
<form action="/order" method="post">
    <% if (request.getAttribute("foodslist") == null) {%>
    暂无商品
    <%
    } else {
    %>
    <% ArrayList<Foods> foodsList = (ArrayList<Foods>) request.getAttribute("foodslist");%>

    <% for (int i = 0; i < foodsList.size(); i++) {%>
    <%! ArrayList<Long> foodID = new ArrayList();%>
    <%
        foodID.add(foodsList.get(i).getFoodid());
    %>
    选择&数量:<input type="hidden" id="<%=foodsList.get(i).getFoodid() %>numbers"
                 name="<%=foodsList.get(i).getFoodid() %>number" value="0">数量:<input type="text"
                                                                                     name="<%=foodsList.get(i).getFoodid()%>"
                                                                                     id="num<%=foodsList.get(i).getFoodid()%>"
                                                                                     value="0">
    <input type="button" id="add<%=foodsList.get(i).getFoodid()%>" value="+"><input type="button"
                                                                                    id="del<%=foodsList.get(i).getFoodid()%>"
                                                                                    value="-">
    <script>
        $(function () {
            $("#add<%=foodsList.get(i).getFoodid()%>").click(function () {
                var num = $("#num<%=foodsList.get(i).getFoodid()%>").val();
                num++;
                $("#num<%=foodsList.get(i).getFoodid()%>").val(num);
                $("#<%=foodsList.get(i).getFoodid() %>numbers").val(num);
            });
            $("#del<%=foodsList.get(i).getFoodid()%>").click(function () {
                var num = $("#num<%=foodsList.get(i).getFoodid()%>").val();
                num--;
                $("#num<%=foodsList.get(i).getFoodid()%>").val(num);
                $("#<%=foodsList.get(i).getFoodid() %>numbers").val(num);
            });
        });
    </script>
    <%=foodsList.get(i) %>
    <% if ((request.getAttribute("price_".concat(foodsList.get(i).getFoodid() + ""))) != null) {%>
    <mark>此套餐订购金额: <%=request.getAttribute("price_".concat(foodsList.get(i).getFoodid() + ""))%>
    </mark>
    <%}%>


    <br>
    <% }%>

    <input type="submit" value="确认下单">

    <% if (request.getAttribute("cookieState") != null) {%>
    <%=request.getAttribute("cookieState")%> <br>
    <%}%>
    <%if (request.getAttribute("orderHint") != null) {%>
    <%=request.getAttribute("orderHint")%> <br>
    <%}%>

    <%}%>
</form>



<hr>
bug: <br>
1.定完一单再点确认下单报错(直接改掉该button) <br>
2.确认下单的响应速度过慢 <br>
3.物品选择不能为负数,现在可以为负数,js或java代码应限制下 <br>
<br>
<%--展示当前路径--%>
<%=request.getRequestURL()%>
<br>
<a href="/index.jsp">返回首页💗</a>
</body>
</html>
