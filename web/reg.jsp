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
    <form name="orderRegForm">

        <div class="col-lg-2">
            用户名: <input type="text" name="reg_username"  id="reg_username" onfocus="unameFocus()"
                        onblur="unameChange()" />
         <span id="nameSp1"></span>
        </div>
        <div class="col-lg-3">
            密码: <input type="password"  name="reg_password" id="reg_password" onfocus="upass1Focus()"
                       onblur="testPwd()" />
        <span id="pass1Sp"></span>
        </div>
        <div class="col-lg-3">
            重复密码: <input type="password" name="reg_password2" id="reg_password2" onfocus="upass2Focus()"
                         onblur="testPwd()" />
        <span id="pass2Sp"></span>
        </div>
        <div class="col-lg-3">
            真实姓名: <input type="text" name="reg_truename" id="reg_truename" onblur="trueNameChange()" onchange="trueNameChange()"><span id="trueNameSp"></span>
        </div>
        <br>
        <div class="col-lg-5">
            收货地址(可更改): <input type="text"  name="reg_address" id="reg_address" onblur="addrChange()" onchange="addrChange()"><span id="addrSp"></span>
        </div>
        <div class="col-lg-3">
            移动电话: <input type="text"  name="reg_phone" id="reg_phone" onblur="phoneChange()" onchange="phoneChange()"><span id="phoneSp"></span>
        </div>
        <br>
        <br>
        <input type="button" value="提交注册" onclick="submitFunc()"><br>
        <input type="reset" value="重置">

    </form>
</div>
<br>
<hr>
<a href="index.jsp">返回首页</a>
<hr>
1.注册页面的UML未连接,有些地方可能不妥当 <br>
2.未加入正则表达式 <br>
3.未加入AJAX判定用户名是否已经存在 <br>
<%=
request.getRequestURL()
%>
</body>
<script src="js/reg2.js"></script>
</html>