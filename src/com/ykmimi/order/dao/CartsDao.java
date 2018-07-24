package com.ykmimi.order.dao;

import com.ykmimi.order.util.JDBCUtil;
import com.ykmimi.order.util.SequenceNextVal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CartsDao {


    public long insertNewCart(long customerID, String orderContent) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        long seqNumOfCartID = 0;
        seqNumOfCartID = SequenceNextVal.getSequenceNextVal("seq_carts");
        conn = JDBCUtil.getConnection();
        String sql ="insert into Carts values(?,?,?)";
        ps = conn.prepareStatement(sql);
        ps.setLong(1,seqNumOfCartID);
        ps.setLong(2,customerID);
        ps.setString(3,orderContent);
        ps.executeUpdate();


        JDBCUtil.close(null,ps,null);
        return seqNumOfCartID;
    }

    /////* 再一个用户登陆的话,此处要归0,从CartsService里调用
    private long seqNumOfCartID;//购物车ID,用于返回

    public long getSeqNumOfCartID() {
        return seqNumOfCartID;
    }

    //*当CartsService执行完一系列的food_id,food_numbers传入,设置seqNumOfCartID=0
    public void setSeqNumOfCartID(long seqNumOfCartID) {
        this.seqNumOfCartID = seqNumOfCartID;
    }

    /////*
    public long insertNewCart(long customer_id, long food_id, int food_numbers) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        if(seqNumOfCartID==0){
            seqNumOfCartID = SequenceNextVal.getSequenceNextVal("seq_carts");
        }
        try {
            conn = JDBCUtil.getConnection();
            String sql = "insert into Carts values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, seqNumOfCartID);
            ps.setLong(2, customer_id);
            ps.setLong(3, food_id);
            ps.setInt(4, food_numbers);
            ps.executeUpdate();
        }finally{

        }
        JDBCUtil.close(null,ps,null);

        return seqNumOfCartID;
    }

}
