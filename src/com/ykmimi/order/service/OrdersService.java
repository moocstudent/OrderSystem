package com.ykmimi.order.service;

import com.ykmimi.order.dao.OrdersDao;
import com.ykmimi.order.entity.Customers;
import com.ykmimi.order.entity.Orders;
import com.ykmimi.order.entity.ShowOrders;
import com.ykmimi.order.util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class OrdersService {

    /////* 点餐 通过foodID和选定数量决定一餐
    public void OrderFoods(int foodID, int foodNumbers) {

    }


    /////* 先通过foodID获取foodname
    public void OrderFoods(int foodID) {

    }

    private OrdersDao od1 = new OrdersDao();

    /////* 创建新的订单,返回订单编号和订单状态
    public long[] createNewOrder(long cartID, Customers c1, double totalPrice, int orderState) {
        Connection conn = null;
        long[] orderIDAndOrderState = new long[2];
        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);

            orderIDAndOrderState = od1.insertNewOrder(cartID, c1, totalPrice, orderState);

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        }
        return orderIDAndOrderState;
    }

    /////*  付款扣款及更改状态0->1
    public int payForOrder(long orderID,long customerID) {

        System.out.println("get in method->payForOrder()");
        int upState = 0;
        int changeOrderState = 0;
        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);
            //更改用于余额
            upState = od1.updateCBalance(orderID,customerID);
            System.out.println("upState:"+upState);
            //更改订单状态
            changeOrderState = od1.updateOrderState(0, 1, orderID);

            conn.commit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();//事务回滚
                    return -2;
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
        }
        if (changeOrderState == 0 || upState==0) {
            return 0;
        }
        return changeOrderState;
    }

    public ShowOrders showAlreadyPaidOrder(long orderID, long cartID) {
        ShowOrders showOrder = null;
        try {
            showOrder = od1.queryOrderAlreadyPaid(orderID, cartID);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
        }
        return showOrder;
    }
}
