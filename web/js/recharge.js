/*
用户充值页面
1.用户充值金额不能为空或0
2.不能充值负数
 */
var inputMoneyObj = document.querySelector("#input_money");
var span = inputMoneyObj.nextSibling;

function inputFocus() {
    console.log("inputFocus()");
    return moneyJudger();
}

function inputBlur() {
    console.log("inputBlur()");
    return moneyJudger();
}

function moneyJudger() {
    var money = inputMoneyObj.value;
    if (money == 0 || money == "" || money == null) {
        span.innerHTML = "充值金币不能为0或为空";
        span.style.color="red";
        return false;
    } else if (money < 0) {
        span.innerHTML = "充值金币不能为负数!";
        span.style.color="red";
        return false;
    } else {
        span.innerHTML = "您将充值" + money + "元."
        return true;
    }
}

//*确认充值提交
function rechargeSubmit() {
    var money = inputMoneyObj.value;

    if (moneyJudger()) {
        document.forms["rechargeForm"].action = "/recharge";
        document.forms["rechargeForm"].method = "post";
        document.forms["rechargeForm"].submit();
        span.innerHTML = "成功充值!充值金额:" + money + "元";
    } else {
        // span.innerHTML="充值失败,请检查输入金钱渠道是否正常或金额是否充足!";
    }


}
