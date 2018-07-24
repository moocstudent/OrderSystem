package com.ykmimi.order.service;

import com.ykmimi.order.dao.FoodsDao;
import com.ykmimi.order.entity.Foods;

import java.sql.SQLException;
import java.util.ArrayList;

public class FoodsService {


    private FoodsDao fd = new FoodsDao();

    /////* 获取食品套餐
    public void getFoods(String foodsname) {


    }


    /////* 获取全部套餐信息
    public ArrayList<Foods> getAllFoodsInfo() {

        ArrayList<Foods> foodList = null;
        String foodInfo = "";
        try {
            foodList = fd.selectAllFoodsForShow();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        }
        if (foodList != null) {
            return foodList;
        }
        return foodList;


    }


    /////* 根据套餐ID获取套餐实例
    public Foods getFoodsInsByID(long foodID) {
        Foods foods = null;
        try {
            foods = new Foods();

            foods = fd.queryFoodInsByID(foodID);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

        }

        return foods;
}

    /////* 根据套餐ID获取套餐实例的ArrayList
    public ArrayList<Foods> getFoodsInsByID(ArrayList<Long> foodIDList) {
        Foods foods = null;
        ArrayList<Foods> foodsInsList = null;
        for (int i = 0; i < foodIDList.size(); i++) {
            if (foods == null) {
                if (foodsInsList == null) {
                    foodsInsList = new ArrayList<Foods>();
                }
                foods = new Foods();

            }
            try {
                foods = fd.queryFoodInsByID(foodIDList.get(i));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                foodsInsList.add(foods);
            }

        }

        return foodsInsList;
    }
}
