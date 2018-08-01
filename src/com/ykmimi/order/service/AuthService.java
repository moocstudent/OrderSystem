package com.ykmimi.order.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
            if (conn != null) {
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

    /////* 用户充值方法,返回1为更改金额成功,0为失败,-4为钱数是0
    public double[] rechargeBalance(long customerID, double money) {
        double[] queryStateAndMoney = new double[2];
        queryStateAndMoney[0]=0;
        queryStateAndMoney[1]=0;
        if(money==0){
            return queryStateAndMoney;//*如果有可能出现充值0元,则返回状态码-4
        }

        int insertState = 0;
        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);
            insertState = ad.changeBalance(customerID, money);
            queryStateAndMoney = ad.queryBalanceByID(customerID);
            conn.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            if (conn!=null){
                try {
                    conn.rollback();
                    queryStateAndMoney[0]=-4;
                    return queryStateAndMoney;
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
        }
        if (insertState==0){
            return null;
        }
        return queryStateAndMoney;
    }//* End RechargeBalance


    /////* 用户更新数据,
    public int[] changeUserInfoByInputData(long cid,ArrayList<Object> changeUserData){

        //changeUserData这个Array的各个下标的代表为:
        //下标0: 用户实名是否更改,更改为实名(String),不更改为"0"(String)
        //下标1: 用户地址是否更改,更改为地址(String),不更改为"0"(String)
        //下标2: 用户电话是否更改,更改为电话(long),  不更改为"0"(String)
        System.out.println("进入changeUserInfoByInputData");
        /////
        //下标0: 用户实名是否更改,更改为实名(String),不更改为0(int)
        int trueNameState = 0;
        if(!(((String)changeUserData.get(0)).equals("0"))){
            try {
                System.out.println("进入了truename的更改service");
                trueNameState = ad.updataTruenameByID(cid,(String)changeUserData.get(0));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        //下标1: 用户地址是否更改,更改为地址(String),不更改为"0"(String)
        int addressState = 0;
        if (!(((String)changeUserData.get(1)).equals("0"))){
            try {
                System.out.println("进入了地址更改的service");
                addressState = ad.updataAddressByID(cid,(String)changeUserData.get(1));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        //下标2: 用户电话是否更改,更改为电话(long),  不更改为"0"(String)
        int phoneState = 0;
        if (!(((String)changeUserData.get(2)).equals("0"))){
            try {
                System.out.println("进入了phone的更改service");
                long phone = Long.parseLong(changeUserData.get(2)+"");
                phoneState = ad.updataPhoneByID(cid,phone);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        int[] updateState = new int[3];
        updateState[0] = trueNameState;
        updateState[1] = addressState;
        updateState[2] = phoneState;
        return updateState;
    }

}
