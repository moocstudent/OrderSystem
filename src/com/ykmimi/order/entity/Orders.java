package com.ykmimi.order.entity;

import java.util.Date;

public class Orders {
    private long orderID;
    private long cartID;
    private long customerID;
    private String customerAddress;
    private long customerPhone;
    private double totalPrice;
    private Date orderDate;
    private int orderState;

    /////* stuff of table Customers
    private String customerTruename;
    /////* stuff of table Carts ↓↓↓
    private String cartContents;

    /////* 出单表
    public Orders(long orderID, long cartID, String cartContents, double totalPrice, String customerTruename, long customerPhone, String customerAddress, Date orderDate) {
        this.orderID = orderID;
        this.cartID = cartID;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.customerTruename = customerTruename;
        this.cartContents = cartContents;
    }

    public Orders(long orderID, long cartID, long customerID, String customerAddress, long customerPhone, double totalPrice, Date orderDate, int orderState, String cartContents) {
        this.orderID = orderID;
        this.cartID = cartID;
        this.customerID = customerID;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.orderState = orderState;
        this.cartContents = cartContents;
    }

    public Orders(long orderID, long cartID, long customerID, String customerAddress, long customerPhone, double totalPrice, Date orderDate, int orderState) {
        this.orderID = orderID;
        this.cartID = cartID;
        this.customerID = customerID;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.orderState = orderState;
    }

    public Orders(long cartID, long customerID, String customerAddress, long customerPhone, double totalPrice, int orderState) {
        this.cartID = cartID;
        this.customerID = customerID;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.totalPrice = totalPrice;
        this.orderState = orderState;
    }

    public Orders() {

    }


    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public long getCartID() {
        return cartID;
    }

    public void setCartID(long cartID) {
        this.cartID = cartID;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public long getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(long customerPhone) {
        this.customerPhone = customerPhone;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

//    @Override
//    public String toString() {
//        return "订单详情:\n" +
//                "订单流水号:" + orderID +
//                "\n购物车编号" + cartID +
//                "\n客户ID:" + customerID +
//                "\n客户地址:" + customerAddress +
//                "\n客户电话:" + customerPhone +
//                "\n订单内容:" + cartContents +
//                "总价:" + totalPrice +
//                "\n下单时间:" + orderDate +
//                "\n订单状态:" + orderState
//                ;
//    }


    @Override
    public String toString() {
        return "\n-----------------------" +
                "\n-----------------------\n" +
                "购物订单详情:"+
                "\n-----------------------" +
                "\n下单时间:" + orderDate +
                "\n订单流水号:" + orderID +
                "\n购物篮编号:" + cartID +
                "\n购物内容:\n" + cartContents +
                "总价格:" + totalPrice +
                "\n-----------------------" +
                "\n客户姓名:" + customerTruename +
                "\n客户电话:" + customerPhone +
                "\n客户地址:" + customerAddress +
                "\n备注:无"+
                "\n-----------------------" +
                "\n-----------------------";


    }
}
