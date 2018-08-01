package com.ykmimi.order.entity;

import java.sql.Date;
import java.util.ArrayList;

/**
 * 用于展示已支付的订单详情
 */
public class ShowOrders {//创建属性快捷方式,粘贴数据库相关设置--> Dao中
    private long order_id;
    private Date order_date;
    private double total_price;
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
    public ShowOrders(long order_id, Date order_date, double total_price, ArrayList<Long> foodIDList, ArrayList<String> foodNameList, ArrayList<Integer> foodNumbersList, String customer_truename, String customer_address, long customer_phone) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.total_price = total_price;
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

        String foodIDNameNumbers = "";
        for (int i = 0; i < foodIDList.size(); i++) {
            foodIDNameNumbers += foodIDList.get(i) + " " + foodNameList.get(i) + " (" + foodNumbersList.get(i) +"份)"+ "<br>\n";
        }
        return "订单详情:<br>\n" +
                "订单ID:" + order_id + "<br>\n" +
                "下单时间:" + order_date + "<br>\n" +
                "套餐ID\t套餐名称\t套餐数量<br>\n" +
                foodIDNameNumbers +
                "总额:" + total_price + "<br>\n" +
                "客户姓名:" + customer_truename + "<br>\n" +
                "客户地址:" + customer_address + "<br>\n" +
                "客户电话" + customer_phone;
    }
}
