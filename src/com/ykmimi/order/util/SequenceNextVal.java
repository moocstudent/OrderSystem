package com.ykmimi.order.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceNextVal {

    public static long getSequenceNextVal(String seqName){

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long seqNum = 0;
        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);
            String sql;
            sql = "select nextval "+" ('"+seqName+"')";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                seqNum = rs.getLong(1);
            }
            conn.commit();
        } catch (SQLException e) {
            if (conn!=null){
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
        return seqNum;
    }
}
