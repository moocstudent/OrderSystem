package com.ykmimi.order.dao;

import com.ykmimi.order.util.JDBCUtil;
import com.ykmimi.order.util.SequenceNextVal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CartsDao {


    /////* 老用法,orderContent原来用于保存所有的购买物品信息为一个整合的String
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

    /////* 创建一个购物车表的元组,单词购买创建一个序列号,但执行多次的表元组插入,这个序列是不唯一的.
    /////* 返回这次的购物车表序列号
    public long insertNewCart(long customer_id, long food_id, int food_numbers) throws SQLException, ClassNotFoundException {
        int insertState = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        if(seqNumOfCartID==0){//如果这次的购物车ID序列是0,则获取一个序列值,用于创建seqNumOfCartID
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
            insertState = ps.executeUpdate();
        }finally{
            JDBCUtil.close(null,ps,null);
        }
        /////* 如果insert状态为0,则是插入数据失败,返回-1
        if(insertState==0){
            return -1;
        }
        /////* 数据插入正常,insertState为1,返回cartID的序列
        return seqNumOfCartID;
    }

}
