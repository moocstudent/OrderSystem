/*
判定用户更改信息:
1.实名信息
2.收货地址
3.用户电话
当只填入某些内容时,只更改填入的那些内容
 */

//都判定所有字段的输入与否,之后执行不同的update SQL
/////真实名称方法
//真实姓名关注时
/*var trueNameObject = document.querySelector("#change_truename");
function trueNameFocFunc() {
    //先获取值,再返回判定结果
    var trueNameOri = trueNameObject.value;
    return trueNameOri.trueNameJudger();
}

function trueNameBlurFunc() {
    //先获取值,再返回判定结果
    var trueNameOri = trueNameObject.value;
    var trueName = trueNameOri.forMatInput();

}*/
/////先格式化并判定数据,如果符合规则,返回true,并给div赋值这个值,否则赋值0
var trueNameSp = document.getElementById("truenameSp");
var trueNameDiv = document.getElementById("truenameDiv");
function trueNameChange(){
    var trueNameFlag =  document.querySelector("#change_truename").value.forMatInput();
    console.log(trueNameFlag);
    if(trueNameFlag==-1){
        trueNameSp.innerHTML= "<font color='red'>真实名称中间不能包含空格,默认不进行修改.</font>";
        trueNameDiv.value = 0;//不好用,改用JSON!!!
        return false;
    }else if (trueNameFlag==0){
        trueNameSp.innerHTML = "<font color='red'>真实名称不能为空(或空格),默认不进行修改.</font>";
        trueNameDiv.value = 0;
        return false;
    }else if(trueNameFlag.length<2 || trueNameFlag.length>10){
        trueNameSp.innerHTML = "<font color='red'>× 真实名称不能小于2位,不能超过10位</font>";
        trueNameDiv.value = 0;
        return false;
    }else{
        console.log("真实姓名可以插入数据库了.");
        trueNameSp.innerHTML = "<font color='#79D335'> √ </font>";
        trueNameDiv.value = trueNameFlag;
        return true;
    }
}
/////
/////* address 先格式化并判定数据,如果符合规则,返回true,并给div赋值这个值,否则赋值0
var addrSp = document.getElementById("addrSp");
var addrDiv = document.getElementById("addrDiv");
function addrChange(){
    var addrFlag = document.getElementById("change_address").value.forMatInput();
    console.log(addrFlag);
    if(addrFlag==-1){
        addrSp.innerHTML= "<font color='red'>地址中间不能包含空格.默认不修改.</font>";
        addrDiv.value=0;
        return false;
    }else if (addrFlag==0){
        addrSp.innerHTML = "<font color='red'>收货地址不能为空(空格).默认不修改.</font>";
        addrDiv.value=0;
        return false;
    }else if(addrFlag.length<2 || addrFlag.length>100){
        addrSp.innerHTML = "<font color='red'>× 收货地址不能小于2位,不能超过100位.</font>";
        addrDiv.value=0;
        return false;
    }else{
        console.log("收货地址可以插入数据库了.");
        addrSp.innerHTML = "<font color='#79D335'> √ </font>";
        addrDiv.value=addrFlag;
        return true;
    }
}
/////* phone 先格式化并判定数据,如果符合规则,返回true,并给div赋值这个值,否则赋值0
var phoneSp = document.getElementById("phoneSp");
var phoneDiv = document.getElementById("phoneDiv");
function phoneChange(){
    var phoneFlag = document.getElementById("change_phone").value.forMatInput();
    console.log(phoneFlag);
    if(phoneFlag==-1){
        phoneSp.innerHTML= "<font color='red'>手机号不能包含空格.默认不修改.</font>";
        phoneDiv.value=0;
        return false;
    }else if (phoneFlag==0){
        phoneSp.innerHTML = "<font color='red'>手机号不能为空(空格).默认不修改.</font>";
        phoneDiv.value=0;
        return false;
    }else if(phoneFlag.length!=11){
        phoneSp.innerHTML = "<font color='red'>× 手机号必须是11位的符合正则表达式的...</font>";
        phoneDiv.value=0;
        return false;
    }else{
        console.log("手机号可以插入数据库了.");
        phoneSp.innerHTML = "<font color='#79D335'> √ </font>";
        phoneDiv.value=phoneFlag;
        return true;
    }

}
/*
var truenameSp = document.getElementById("truenameSp");
Object.prototype.trueNameJudger = function(){
    //先trim再判定字段格式,格式不正确返回false,格式正确返回true
    console.log("in trueNameJudger protoType");
    //去真实名称的前后导空格.
    var trueName =  this.replace(/^\s+/,"").replace(/\s+$/);
    if(trueName==""){
        console.log("trueName==\"\"");
        truenameSp.innerHTML = "真实姓名如果为空则为不修改.";
        return false;
    }else{
        console.log("trueName=="+trueName);
        truenameSp.innerHTML = "真实姓名将修改为"+trueName;
        return true;
    }
}*/






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



/////* 判定输入的至少要有一项内容符合条件

/////* 更新资料
function upDataSubMit(){
    if(trueNameChange() || addrChange() || phoneChange()){
        document.forms["updataForm"].action="/changeData";
        document.forms["updataForm"].method="post";
        document.forms["updataForm"].submit();
    }else{
        document.querySelector("#submitButtonSp").innerHTML= "没有要更新的数据.";
    }
}


