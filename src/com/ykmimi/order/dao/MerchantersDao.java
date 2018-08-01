package com.ykmimi.order.dao;

import com.ykmimi.order.entity.Foods;
import com.ykmimi.order.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MerchantersDao {

    /////* 插入套餐信息
    public void insertFoodsToTable(Foods food) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into foods(" +
                "food_name,food_brief,food_price)" +
                "values(?,?,?)";
        conn = JDBCUtil.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1,food.getFoodname());
        ps.setString(2,food.getFoodbrief());
        ps.setDouble(3,food.getPrice());
        ps.executeUpdate();

        JDBCUtil.close(null,ps,null);
    }
}
