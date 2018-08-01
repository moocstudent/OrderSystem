1.加入Session机制,必须

2.<del>cookie加强使用</del>

3.CartsDao的食品ID和食品数量的传入批处理加强(executeBatch??)

4.显示已下单但未付款的订单  select * from Orders where customer_id = ?

5.余额不足的提示及跳转充值. OK

6.各页面的Session过期时提醒 if...!=null + else

7.当重复提交订单时,订单的状态已经为1了,所以再查找时查找那个订单的价格会返回null,之后报错.进入exception
(需要把重复提交订单 和 数据库插入修改的其他错误分别出来)

8.Cookie : 如果登陆过了,再回登陆页面,判断Cookie的值,填充登陆的input value的值

9.将用户实名传送到现在的session中的用户对象 并重新赋值? 可以重新赋值session的对象吗
(将原session移除,再放入)