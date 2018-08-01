package com.ykmimi.order.service;

import com.ykmimi.order.dao.CartsDao;
import com.ykmimi.order.util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartsService {

    private CartsDao cd = new CartsDao();

    /////* 传入Dao层执行insert 会有事务处理
    public long createCart(long customerID, String orderContent) {
        Connection conn = null;
        long cartID = 0;
        try {

            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);
            cartID = cd.insertNewCart(customerID, orderContent);
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
        return cartID;


    }///// createCart END


    /////* 创建新购物车,返回购物车ID ,for循环将food_id,和food_numbers传入Dao
    public long createNewCart(long customer_id, ArrayList<Long> food_id, ArrayList<Integer> food_numbers) {
        Connection conn = null;
        long cartID = 0;//购物车ID
        try {
            conn = JDBCUtil.getConnection();
            for (int i = 0; i < food_id.size(); i++) {
                conn.setAutoCommit(false);
                cartID = cd.insertNewCart(customer_id, food_id.get(i), food_numbers.get(i));
                if (cartID==-1){
                    //如果返回-1,则是Dao的insert出错,插入数据失败了
                    return -1;
                }
                conn.commit();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            cd.setSeqNumOfCartID(0);
        }
        return cartID;
    }///// createCart END

}
