package com.ykmimi.order.dao;

import com.ykmimi.order.entity.Foods;
import com.ykmimi.order.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodsDao {


    /////* 获取所有套餐详情
    public ArrayList<Foods> selectAllFoodsForShow() throws SQLException, ClassNotFoundException {

        Foods food = null;
        ArrayList<Foods> foodList = null;
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        String sql = "select * from foods";
        conn = JDBCUtil.getConnection();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            food = new Foods(
                    rs.getLong("food_id"),
                    rs.getString("food_name"),
                    rs.getString("food_brief"),
                    rs.getDouble("food_price"));

            if (foodList == null) {
                foodList = new ArrayList<Foods>();
            }
            foodList.add(food);

        }
        JDBCUtil.close(rs, ps, null);
        return foodList;

    }

    /////* 通过id获取食物实例
    public Foods queryFoodInsByID(Long foodID) throws SQLException, ClassNotFoundException {
        Foods foods = null;
        Connection conn=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from Foods where food_id = ?";
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, foodID);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (foods == null) {
                    foods = new Foods(
                            rs.getLong(1),//food_id
                            rs.getString(2),//food_name
                            rs.getString(3),//food_brief
                            rs.getDouble(4)//food_price
                    );
                }
            }
        }finally{
            JDBCUtil.close(rs,ps,null);
        }
        return  foods;

    }

    /////
//    public Foods selectFoodInsByName(String foodName){
//
//        Connection conn = JDBCUtil.getConnection();
//        String sql = "select * from foods where food_name = ?";
//        ResultSet rs = null;
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setString(1,foodName);
//        rs = ps.executeQuery();
//        while(rs.next()){
//
//            long userid = rs.getString("userid");
//            String uname = rs.getString("user_name");
//            String pwd = rs.getString("user_password");
//            String truename = rs.getString("true_name");
//            long
//
//        }
//
//    }

}
