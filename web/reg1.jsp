<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>注册页面</title>

</head>
<body>
<div class="container-fluid">
    <form id="orderRegForm">

        <div class="col-lg-2">
            用户名: <input type="text" class="form-control" name="reg_username" onfocus="nameFocus(this.value)"
                        onblur="nameBlur(this.value)" />
        </div><span id="nameSp1"></span>
        <div class="col-lg-3">
            密码: <input type="password" class="form-control" name="reg_password" onfocus="pass1Focus(this.value)"
                       onblur="pass1Blur(this.value)" />
        </div><span id="pass1Sp"></span>
        <div class="col-lg-3">
            重复密码: <input type="password" class="form-control" name="reg_password2" onfocus="pass2Focus(this.value)"
                         onblur="pass2Blur(this.value)" />
        </div><span id="pass2Sp"></span>
        <div class="col-lg-3">
            真实姓名: <input type="text" class="form-control" name="reg_truename">
        </div>
        <br>
        <div class="col-lg-5">
            收货地址(可更改): <input type="text" class="form-control" name="reg_address">
        </div>
        <div class="col-lg-3">
            移动电话: <input type="text" class="form-control" name="reg_phone">
        </div>
        <br>
        <br>
        <input type="button" value="提交注册" onclick="formSubmitFunc()" class="btn btn-primary btn-lg">

    </form>
</div>
<br>
1.注册页面的UML未连接,有些地方可能不妥当
<%=
request.getRequestURL()
%>
</body>
<script src="js/reg.js"></script>
</html>