package com.ykmimi.order.entity;

import java.sql.Date;
import java.util.ArrayList;

/**
 * 用于展示已支付的订单详情
 */
public class ShowOrders {//创建属性快捷方式,粘贴数据库相关设置--> Dao中
    private long order_id;
    private Date order_date;
//    private long food_id;
    private ArrayList<Long> foodIDList;
//    private String food_name;
    private ArrayList<String> foodNameList;
//    int food_numbers;
    ArrayList<Integer> foodNumbersList;
    String customer_truename;
    String customer_address;
    long customer_phone;

    /////* 获取已支付的订单,展示给用户
    public ShowOrders(long order_id, Date order_date, ArrayList<Long> foodIDList, ArrayList<String> foodNameList, ArrayList<Integer> foodNumbersList, String customer_truename, String customer_address, long customer_phone) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.foodIDList = foodIDList;
        this.foodNameList = foodNameList;
        this.foodNumbersList = foodNumbersList;
        this.customer_truename = customer_truename;
        this.customer_address = customer_address;
        this.customer_phone = customer_phone;
    }

    public ShowOrders() {

    }

    @Override
    public String toString() {
        return "ShowOrders{" +
                "order_id=" + order_id +
                ", order_date=" + order_date +
                ", foodIDList=" + foodIDList +
                ", foodNameList=" + foodNameList +
                ", foodNumbersList=" + foodNumbersList +
                ", customer_truename='" + customer_truename + '\'' +
                ", customer_address='" + customer_address + '\'' +
                ", customer_phone=" + customer_phone +
                '}';
    }
}
