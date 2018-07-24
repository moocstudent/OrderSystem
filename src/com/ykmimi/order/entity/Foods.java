package com.ykmimi.order.entity;

import java.util.ArrayList;

public class Foods {
    private long foodid;//食品id
    private String foodname;//食品名
    private String foodbrief;//食品内容简介
    private double price;//食品价格
    private static ArrayList<Foods> foodsList = new ArrayList<>();//食品列表

    public Foods() {

    }

    public ArrayList<Foods> getFoodsList() {
        return foodsList;
    }

    public void setFoodsList(ArrayList<Foods> foodsList) {
        this.foodsList = foodsList;
    }

    public long getFoodid() {
        return foodid;
    }

    public void setFoodid(long foodid) {
        this.foodid = foodid;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getFoodbrief() {
        return foodbrief;
    }

    public void setFoodbrief(String foodbrief) {
        this.foodbrief = foodbrief;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /////* 设置产品用
    public Foods(String foodname, String foodbrief, double price) {
        this.foodname = foodname;
        this.foodbrief = foodbrief;
        this.price = price;
    }

    /////* 获取产品用
    public Foods(long foodid, String foodname, String foodbrief, double price) {
        this.foodid = foodid;
        this.foodname = foodname;
        this.foodbrief = foodbrief;
        this.price = price;
    }


    @Override
    public String toString() {
        return  "套餐编号:" + foodid +
                ",套餐名:" + foodname +
                ",简介:" + foodbrief +
                ",价格:" + price;
    }
}


