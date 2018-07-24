package com.ykmimi.order.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ykmimi.order.dao.AuthDao;
import com.ykmimi.order.entity.Customers;
import com.ykmimi.order.util.JDBCUtil;

/**
 * 登陆注册等认证service
 *
 * @author SeeClanUkyo
 */
public class AuthService {
    private AuthDao ad = new AuthDao();

    /////* 登陆认证 返回用户id
    public long loginByNameAndPassWord(String username, String password) {
        long cid = 0;

        try {
            cid = ad.queryUserByNameAndPassword(username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            if (ad != null) {
                cid = -1;
                return cid;
            }

        } finally {

        }
        return cid;
    }/////* End loginByNameAndPassWord

    /////* 通过用户ID查询用户实例返回给showUser
    public Customers getCustomersInstanceByID(long cid) {
        Customers customer = null;

        try {
            customer = ad.queryCustomerByID(cid);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return customer;

    }/////* End getCustomersInstanceByID

    /////* 注册新用户 如果注册成功返回用户id 0为注册失败,-1为进行了异常获取
    public long registerNewCustomer(Customers customer) {
        long cusID = 0;
        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);
            cusID = ad.insertNewCustomer(customer);
            conn.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            if(conn!=null){
                try {
                    conn.rollback();
                    return -1;
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
        }
        //* 如果cusID为获取的id号,则证明注册成功了,
        //* 如果是0,则说明没有注册成功.
        return cusID;

    }/////* End registerNewCustomer





}
