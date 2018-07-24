<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>

</head>
<body>
<div class="container-fluid">
<form action="/register" method="post">

    <div class="col-lg-2">
<font>用户名:</font> <input type="text" class="form-control" name="reg_username">
    </div>
    <div class="col-lg-3">
<font>密码:</font> <input type="password" class="form-control" name="reg_password">
    </div>
    <div class="col-lg-3">
<font>重复密码:</font> <input type="password" class="form-control" name="reg_password2">
    </div>
    <div class="col-lg-3">
<font>真实姓名:</font> <input type="text" class="form-control" name="reg_truename">
    </div><br>
    <div class="col-lg-5">
<font>收货地址(可更改):</font> <input type="text" class="form-control" name="reg_address">
    </div>
    <div class="col-lg-3">
<font>移动电话:</font> <input type="text" class="form-control" name="reg_phone">
    </div>
    <br>
    <br>
<input type="submit" value="提交注册" class="btn btn-primary btn-lg">

</form>
</div>
<br>
1.注册页面的UML未连接,有些地方可能不妥当
<%=
request.getRequestURL()
%>
</body>
</html>