/**
 *  用户注册界面JS
 */
var nameSp1 = document.querySelector("#nameSp1");
var pass1Sp = document.querySelector("#pass1Sp");
var pass2Sp = document.querySelector("#pass2Sp");

var nameObj = document.querySelector("#reg_username");
var nameFlag = false;
// ///*[[用户名的焦点和更改时方法]]
// 验证用户名格式长度.等,onfocus时
function unameFocus() {
    var name = nameObj.value;
    nameJudger(name);
}

// 验证用户名格式长度,等, onChange时
function unameChange() {
    var name = nameObj.value;
    return nameJudger(name);
}

function nameJudger(name) {
    var nameformat = name.forMatInput();
    if (nameformat == 0) {
        console.log("name empty,nameFlag = false;");
        nameSp1.innerHTML = "用户名不能为空(空格)";
        nameSp1.style.color = "red";
        nameFlag = false;
    } else if (nameformat == -1) {
        console.log("name has space in middle,nameFlag = false;");
        nameSp1.innerHTML = "用户名中间不能有空格";
        nameSp1.style.color = "red";
        nameFlag = false;
    } else {
        if (nameformat.length > 14 || nameformat.length < 6) {
            console.log("name error less than 6 or more than 14,nameFlag = false;");
            nameSp1.innerHTML = "用户名不能小于6位,不能大于14位";
            nameSp1.style.color = "red";
            nameFlag = false;
        } else {
            console.log("name right √,nameFlag = true;");
            nameSp1.innerHTML = "用户名可用.";
            nameSp1.style.color = "green";
            nameFlag = true;
            return true;
        }
    }
}


// ///*[[用户密码的判定 重复密码是否相同,长短等]]
// 用户密码1 Focus时
var input1 = document.getElementById("reg_password");
function upass1Focus() {

    // 获取document对象
    if (input1!=null) {
        return testPassword(input1);
    }
}

// 用户密码1 Blur时
function upass1Blur() {

    // 获取document对象
    if (input1!=null) {
        return testPassword(input1);
    }
}

// ///* [[用户密码2判定]]
// 用户密码2 Focus时
var input2 = document.getElementById("reg_password2");
function upass2Focus() {

    // 获取document对象
    if (input2!=null) {
        return testPassword(input2);
    }

}

// 用户密码2 Blur时
function upass2Blur() {


    // 获取document对象
    if (input2!=null){
        return testPassword(input2);
    }


}




/////* 判定密码的格式是否正确
function testPassword(input) {
    //获得这个密码所在input的下个span用于显示信息
    console.log("in testPassword(input)");
    var passSpan = input.nextSibling;
    pass = input.value;
    pass = pass.forMatInput();

    if (pass == 0) {
        console.log("testPassword(input)->pass==0");
        passwordFlag = false;
        passSpan.innerHTML = "<font color='red'>密码不能为空(空格)</font>";
        return false;

    } else if (pass == -1) {
        console.log("testPassword(input)->pass==-1");
        passwordFlag = false;
        passSpan.innerHTML = "<font color='red'>密码中间不能有空格</font>";
        return false;
    } else if (pass.length < 6 || pass.length > 16) {
        console.log("testPassword(input)->pass.length < 6 || pass.length > 16");
        passwordFlag = false;
        passSpan.innerHTML = "<font color='red'>密码不能小于6位,不能大于16位.中间不能包含空格</font>";
        return false;
    } else {
        //
        console.log("pass 格式 OK");
        //密码格式正确,返回密码
        return pass;

    }
}

////*每次执行的方法
function testPwd() {

    if (passwordJudger(upass1Blur(), upass2Blur())) {
        return true;
    } else {
        return false;
    }
}

/////* trueName 先格式化并判定数据,如果符合规则,返回true
var trueNameSp = document.getElementById("trueNameSp");
function trueNameChange(){
   var trueNameFlag =  document.getElementById("reg_truename").value.forMatInput();
   console.log(trueNameFlag);
   if(trueNameFlag==-1){
       trueNameSp.innerHTML= "<font color='red'>真实名称中间不能包含空格.</font>";
       return false;
   }else if (trueNameFlag==0){
       trueNameSp.innerHTML = "<font color='red'>真实名称不能为空(或空格).</font>";
       return false;
   }else if(trueNameFlag.length<2 || trueNameFlag.length>10){
       trueNameSp.innerHTML = "<font color='red'>真实名称不能小于2位,不能超过10位</font>";
       return false;
   }else{
       console.log("真实姓名可以插入数据库了.");
       trueNameSp.innerHTML = "<font color='#79D335'> √ </font>";
       return true;
   }
}
/////* address 先格式化并判定数据,如果符合规则,返回true
var addrSp = document.getElementById("addrSp");
function addrChange(){
    var addrFlag = document.getElementById("reg_address").value.forMatInput();
    console.log(addrFlag);
    if(addrFlag==-1){
        addrSp.innerHTML= "<font color='red'>地址中间不能包含空格.</font>";
        return false;
    }else if (addrFlag==0){
        addrSp.innerHTML = "<font color='red'>收货地址不能为空(空格).</font>";
        return false;
    }else if(addrFlag.length<2 || addrFlag.length>100){
        addrSp.innerHTML = "<font color='red'>收货地址不能小于2位,不能超过100位</font>";
        return false;
    }else{
        console.log("收货地址可以插入数据库了.");
        addrSp.innerHTML = "<font color='#79D335'> √ </font>";
        return true;
    }
}
/////* phone 先格式化并判定数据,如果符合规则,返回true
var phoneSp = document.getElementById("phoneSp");
function phoneChange(){
    var phoneFlag = document.getElementById("reg_phone").value.forMatInput();
    console.log(phoneFlag);
    if(phoneFlag==-1){
        phoneSp.innerHTML= "<font color='red'>手机号不能包含空格.</font>";
        return false;
    }else if (phoneFlag==0){
        phoneSp.innerHTML = "<font color='red'>手机号不能为空(空格).</font>";
        return false;
    }else if(phoneFlag.length!=11){
        phoneSp.innerHTML = "<font color='red'>手机号必须是11位的符合正则表达式的...</font>";
        return false;
    }else{
        console.log("手机号可以插入数据库了.");
        phoneSp.innerHTML = "<font color='#79D335'> √ </font>";
        return true;
    }

}


/////* input去除空格及判定中间是否有空格(可再强化查看是否含有非法字符)
Object.prototype.forMatInput = function () {
    //清除前导后导空格
    var input = this.replace(/^\s+/, "").replace(/\s+$/, "");
    //改为字符串形式
    input = input + "";
    //如果进行trim后字符串中间含有空格,则返回-1
    if (input.indexOf(" ") > -1) {
        return -1;
    }
    else {
        //如果是空格被trim后返回0
        if (input.length == 0) {
            return 0;
        } else {
            //否则返回trim后的字符串(该字符串中间不含空格(或其他正则的特殊限定符号))
            return input;
        }

    }

}


/*
// ///!* 如果密码一样且格式正确 就判定正确返回 true
if ((upass1 == upass2) && upass1 != false && upass2 != false) {
    upass1Tag.innerHTML = "";
    upass2Tag.innerHTML = "密码格式正确!且两次输入一致!💗";
    upass2Tag.style.color = "green";
    return true;

}
// ///!* 如果密码1格式又错误了,那么提示重新输入密码1,并清空密码2 返回 false
else if (upass1 == false) {
    upass1Tag.innerHTML = "输入密码,密码长度不小于6位.谢谢合作💗";
    upass2Tag.innerHTML = "";
    upass1Tag.style.color = "red";
    upass2Tag.style.color = "red";
    console.log("wrong pass1");
    // *如果密码1格式不正确了,则密码2中密码清空
    document.querySelector("#password2").value = "";
    return false;
}
// ///!* 如果两次密码不匹配,则返回不一致,并返回false
else {
    upass1Tag.innerHTML = "";
    upass2Tag.innerHTML = "两次密码输入不一致.";
    upass2Tag.style.color = "red";
    console.log("wrong pass2");
    // *如果密码1/2格式不正确了或不同了,则密码2中密码清空 这样的话不太友好,所以去掉
    /!* document.querySelector("#password2").value=""; *!/
    return false;

}
*/


// [***judger old method***]
// else if(!upass1Blur()){
// upass1Tag.innerHTML = "输入密码1,密码长度不小于6位.谢谢合作💗";
/* upass1Tag.style.color = "red"; */
// return false;
// }else if(!pass2Blur()){
// upass2Tag.innerHTML = "输入密码2,密码长度不小于6位.谢谢合作💗";
/* upass2Tag.style.color = "red"; */
// return false;
// }
function submitFunc() {
    //如果密码重复正确和用户名判定正确及真实名字,收货地址,电话号码是否格式正确
    if (unameChange() && testPwd() && trueNameChange() && addrChange() && phoneChange()) {
        document.forms["orderRegForm"].action="/register";
        /*document.forms["orderRegForm"].action="http://www.ykmimi.com";*/

        document.forms["orderRegForm"].method="post";
        document.forms["orderRegForm"].submit();

        /*return true;*/
    } else {
        return false;
    }
    //还未判定年龄手机号等.
}


///////////////////////////////GObang


//判定密码两个相同与否
/////* 每个input,将不同的提示给不同的input的子同胞span
function passwordJudger(upass1, upass2) {
    console.log("in judger.");

    // ///* 如果密码一样且格式正确 就判定正确返回 true
    if ((upass1 == upass2) && upass1 != false && upass2 != false) {
        pass1Sp.innerHTML = "";
        pass2Sp.innerHTML = "密码格式正确!且两次输入一致!💗";

        return true;

    }
    // ///* 如果密码1格式又错误了,那么提示重新输入密码1,并清空密码2 返回 false
    else if (upass1 == false) {
        pass1Sp.innerHTML = "输入密码,密码长度不小于6位.谢谢合作💗";
        pass2Sp.innerHTML = "";


        console.log("wrong pass1");
        // *如果密码1格式不正确了,则密码2中密码清空
        input2.value = "";
        return false;
    }
    // ///* 如果两次密码不匹配,则返回不一致,并返回false
    else {
        pass1Sp.innerHTML = "";
        pass2Sp.innerHTML = "两次密码输入不一致.";

        console.log("wrong pass2");
        // *如果密码1/2格式不正确了或不同了,则密码2中密码清空 这样的话不太友好,所以去掉
        /* document.querySelector("#password2").value=""; */
        return false;

    }

}

