var nameSp1 = document.querySelector("#nameSp1");
var pass1Sp = document.querySelector("#pass1Sp");
var pass2Sp = document.querySelector("#pass2Sp");


var nameFlag = false;

//用户名聚焦时,接收this.value
function nameFocus(name) {
     nameJudger(name);
}

//用户名失焦时,接收this.value
function nameBlur(name) {
     nameJudger(name);
}

function nameJudger(name) {
    //name进行格式化判定,如果全是空格,返回0,如果是中间含有空格,返回-1
    //否则返回trim后的name
    /*name = name+"";*/
    var nameformat = name.forMatStr();
    if (nameformat == 0) {
        nameSp1.innerHTML = "用户名不能为空(空格)";
        nameSp1.style.color ="red";
        nameFlag = false;
    } else if (nameformat == -1) {
        nameSp1.innerHTML = "用户名中间不能有空格";
        nameSp1.style.color ="red";
        nameFlag = false;
    } else {
        if (nameformat.length > 14 || nameformat.length < 6) {
            nameSp1.innerHTML = "用户名不能小于6位,不能大于14位";
            nameSp1.style.color ="red";
            nameFlag = false;
        } else {
            nameSp1.innerHTML = "用户名可用.";
            nameSp1.style.color="green";
            nameFlag = true;
        }
    }
}

/////* 下面是密码的判定方法等
//密码两个使用一个Flag,只有当两个密码都符合规则且相等时为true
var passwordFlag = false;


function pass1Focus(pass1) {
    pass1Sp.innerHTML = passJudger(pass1);
}

function pass1Blur(pass1) {
    pass1Sp.innerHTML = passJudger(pass1);
}

//* maybe use
function pass1Change(pass1) {
    pass1Sp.innerHTML = passJudger(pass1);

}

//密码2
function pass2Focus(pass2) {
    pass2Sp.innerHTML = passJudger(pass2);
}

function pass2Blur(pass2) {
    pass2Sp.innerHTML = passJudger(pass2);
}

/////* 判定密码格式 当两次密码相同且符合规则给与nameFlag=true
var passArray = [];
var firstPass = "";
function passJudger(pass) {
    pass = pass .forMatStr();

    if (pass == 0) {
        passwordFlag = false;
        return "<font color='red'>密码不能为空(空格)</font>";
    } else if (pass == -1) {
        passwordFlag = false;
        return "<font color='red'>密码中间不能有空格</font>";
    } else if (pass.length < 6 || pass.length > 16) {
        passwordFlag = false;
        return "<font color='red'>密码不能小于6位,不能大于16位.中间不能包含空格</font>";
    } else {
        //如果符合密码格式的话执行下面
        passArray.push(pass);
        firstPass = pass;
        
        if(firstPass===pass){
            if(passArray[0]===passArray[1]){
                passwordFlag = true;
                return "<font color='green'>两次密码相同√通过</font>"
            }else{
                passArray.pop();//第二个密码输入不同于第一个,则清理
                passwordFlag = false;
                console.log(passArray.length);
                return "<font color='red'>重复密码错误,请重新输入</font>";
            }
        }else{
            passwordFlag = false;//单一密码不能确定true
            return "<font color='green'>密码可用,输入重复密码</font>";
        }
    }
}

//字符串去除空格及判定中间是否有空格(可再强化查看是否含有非法字符)
Object.prototype.forMatStr = function () {
    //字符串进行trim赋值给strF
    var strF = this.replace(/^\s+/,"").replace(/\s+$/,"");
    //如果进行trim后字符串中间含有空格,则返回-1
    if (strF.indexOf(" ") > -1) {
        return -1;
    }
        else {
        //如果是空格被trim后返回0
        if (strF.length == 0) {
            return 0;
        } else {
            //否则返回trim后的字符串(该字符串中间不含空格(或其他正则的特殊限定符号))
            return strF;
        }

    }

}
/////* 进行提交判定

function formSubmitFunc(){
    //如果用户名与密码判定通过则进行注册提交
    if(nameFlag && passwordFlag){
//不能使用id获取表单对象
        document.forms["#orderRegForm"].action="/register";
        document.forms["#orderRegForm"].method="post";
        document.getElementById("orderRegForm").submit();
    //否则
    }else{

    }
}