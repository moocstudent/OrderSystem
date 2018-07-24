package com.ykmimi.order.dao;

import com.ykmimi.order.entity.Customers;
import com.ykmimi.order.entity.Orders;
import com.ykmimi.order.entity.ShowOrders;
import com.ykmimi.order.util.JDBCUtil;
import com.ykmimi.order.util.SequenceNextVal;

import java.sql.*;
import java.util.ArrayList;

public class OrdersDao {

    /////* 设置新的订单,并返回订单编号和订单状态
    public long[] insertNewOrder(long cartID, Customers c1, double totalPrice, int orderState) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        long orderID = 0;
        orderID = SequenceNextVal.getSequenceNextVal("seq_orders");
        long[] orderIDAndOrderState = new long[2];
        orderIDAndOrderState[0] = orderID;
        orderIDAndOrderState[1] = orderState;
        int inserState = 0;
        String sql = "insert into Orders values(?,?,?,?,?,?,current_date,?);";
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, orderID);
            ps.setLong(2, cartID);
            ps.setLong(3, c1.getCustomer_id());
            ps.setString(4, c1.getCustomer_address());
            ps.setLong(5, c1.getCustomer_phone());
            ps.setDouble(6, totalPrice);
            ps.setInt(7, orderState);
            inserState = ps.executeUpdate();
        }finally {
            JDBCUtil.close(null, ps, null);
        }
        //如果插入数据失败则,返回的数据两个值都是0
        if (inserState==0){
            orderIDAndOrderState[0] = 0;
            orderIDAndOrderState[1] = 0;
            return orderIDAndOrderState;
        }
        return orderIDAndOrderState;
    }

    /////* 对订单进行扣款
    public int updateCBalance(long orderID,long customerID) throws SQLException, ClassNotFoundException {
        int upState = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        // 更改账户余额,根据其ID和orderID
        String sql = "update Customers set customer_balance" +
                " = customer_balance - (select total_price " +
                "from Orders where order_id = ?  and order_state = 0)" +
                "where Customers.customer_id = ?";
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, orderID);
            ps.setLong(2,customerID);

            upState = ps.executeUpdate();
        } finally {

            JDBCUtil.close(null, ps, null);
        }
        return upState;
    }


    /////* 对订单状态号进行更改(原本的state,新的state,及orderID)
    public int updateOrderState(int orginalState, int newOrderState, long orderID) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        int changeOrderState = 0;
        /////
        String sql = "update Orders set order_state = ? where order_id = ? and order_state = ?";
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, newOrderState);
            ps.setLong(2, orderID);
            ps.setInt(3, orginalState);
            changeOrderState = ps.executeUpdate();
        } finally {
            JDBCUtil.close(null, ps, null);
        }
        return changeOrderState;
    }

    /////* 虽然orderID和cartID就能确认唯一的订单,但还应加入customer_id去更针对查询
    public ShowOrders queryOrderAlreadyPaid(long orderID, long cartID) throws SQLException, ClassNotFoundException {
        Orders order = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ShowOrders so = null;
//        String sql  = "select * from Orders o1,Carts c1,Customers cus1 where order_id = ? and o1.cart_id=c1.cart_id" +
//                " and cus1.customer_id = o1.customer_id";

        /////* 订单ID,订单时间,订单总额,套餐ID,套餐名称,套餐数量,用户实名,用户地址,用户电话
        String sql = "select o1.order_id,o1.order_date,o1.total_price,carts.food_id,f1.food_name,carts.food_numbers," +
                "c1.customer_truename,c1.customer_address,c1.customer_phone from carts " +
                "left join orders o1 on carts.customer_id = o1.customer_id " +
                "left join Customers c1 on  o1.customer_id = c1.customer_id " +
                "left join Foods f1 on carts.food_id = f1.food_id " +
                "where o1.order_id = ? and carts.cart_id = ? " +
                "and o1.order_state = 1";
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, orderID);
            ps.setLong(2, cartID);
            long order_id = 0;
            Date order_date = null;
            double total_price = 0;
            long food_id = 0;
            ArrayList<Long> foodIDList = null;
            String food_name = null;
            ArrayList<String> foodNameList = null;
            int food_numbers = 0;
            ArrayList<Integer> foodNumbersList = null;
            String customer_truename = null;
            String customer_address = null;
            long customer_phone = 0;
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
                //开始获取数据
                if (order_id == 0) {//获取一次order_id
                    order_id = rs.getLong("order_id");
                }
                if (order_date == null) {//获取一次order_date
                    order_date = rs.getDate("order_date");
                }
                if(total_price==0){//获取一次这个订单的总额
                    total_price = rs.getDouble("total_price");
                }
                //*有几个就获取几个food_id,装入下面的foodIDList
                food_id = rs.getLong("food_id");
                if (foodIDList == null) {
                    foodIDList = new ArrayList<>();
                }
                foodIDList.add(food_id);
                //*有几个就获取几个food_name,装入下面的foodNameList
                food_name = rs.getString("food_name");
                if (foodNameList == null) {
                    foodNameList = new ArrayList<>();
                }
                foodNameList.add(food_name);
                //*有几个就获取几个food_numbers,装入下面的foodNumbersList
                food_numbers = rs.getInt("food_numbers");
                if (foodNumbersList == null) {
                    foodNumbersList = new ArrayList<>();
                }
                foodNumbersList.add(food_numbers);
                if (customer_truename == null) {//获取一次customer_truename
                    customer_truename = rs.getString("customer_truename");
                }
                if (customer_address == null) {//获取一次customer_address
                    customer_address = rs.getString("customer_address");
                }
                if (customer_phone == 0) {//获取一次customer_phone
                    customer_phone = rs.getLong("customer_phone");
                }

            }
            //如果whileNext了,创建这个用于展示的订单对象
            if (count > 0) {
                so = new ShowOrders(
                        order_id,
                        order_date,
                        total_price,
                        foodIDList,
                        foodNameList,
                        foodNumbersList,
                        customer_truename,
                        customer_address,
                        customer_phone
                );
                System.out.println("new ShowOrders OK");
            }
        } finally {
            JDBCUtil.close(rs, ps, null);
        }
        return so;//如果没有创建ShowOrders对象so返回,就返回null
    }
}
