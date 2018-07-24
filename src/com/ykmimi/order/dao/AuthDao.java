package com.ykmimi.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ykmimi.order.entity.Customers;
import com.ykmimi.order.util.JDBCUtil;
import com.ykmimi.order.util.SequenceNextVal;

/**
 * Dao层实现和数据层的直接访问操作
 * @author SeeClanUkyo
 *
 */
public class AuthDao {

	///// * 通过用户名密码查看是否有此账户,有则返回此账户的ID,没有此用户返回0
	public long queryUserByNameAndPassword(String username, String password)
			throws ClassNotFoundException, SQLException {
		long cid = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select customer_id from Customers where customer_name = ? and customer_password = ?";
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				cid = rs.getLong("customer_id");
			}
		} finally {
			JDBCUtil.close(rs, ps, null);
		}
		return cid;

	}///// * End queryUserByNameAndPassword

	///// * 通过用户ID查询用户实例返回给showUser
	public Customers queryCustomerByID(long cid) throws ClassNotFoundException, SQLException {
		Customers customer = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from Customers where customer_id = ?";
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, cid);
			rs = ps.executeQuery();
			while (rs.next()) {

				if (customer == null) {
					customer = new Customers(
							rs.getLong(1), 
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							rs.getLong(6),
							rs.getDouble(7));
				}
			}
		} finally {
			JDBCUtil.close(rs, ps, null);
		}

		return customer;

	}/////* End queryCustomerByID


	/////* 注册新用户
	public long insertNewCustomer(Customers customer) throws SQLException, ClassNotFoundException {
		long cid = SequenceNextVal.getSequenceNextVal("seq_customers");
		int insertState= 0;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into Customers values(?,?,?,?,?,?,0)";
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1,cid);
			ps.setString(2, customer.getCustomer_name());
			ps.setString(3, customer.getCustomer_password());
			ps.setString(4, customer.getCustomer_truename());
			ps.setString(5, customer.getCustomer_address());
			ps.setLong(6, customer.getCustomer_phone());

			insertState = ps.executeUpdate();
			//如果插入数据成功,insertState为1,否则为初始值0
		}finally{
			JDBCUtil.close(null,ps,null);
		}
		if (insertState==0){
			return 0;
		}else if(insertState>0){
			return cid;
		}else{
			return insertState;
		}

	}
	
	
	
}
