package com.ykmimi.order.servlet;

import com.ykmimi.order.entity.Customers;
import com.ykmimi.order.entity.Foods;
import com.ykmimi.order.service.AuthService;
import com.ykmimi.order.service.CartsService;
import com.ykmimi.order.service.FoodsService;
import com.ykmimi.order.service.OrdersService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "OrderServlet")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /////* 字符编码设置
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //* 获取Cookie
        long cid = 0;
        Cookie[] cs = request.getCookies();
        if(cs.length>0){
            for(Cookie c : cs){
                if(c.getName().equals("cidCookie")){
                    //获取Cookie中的customer_id
                    cid = Long.parseLong(c.getValue());
                    break;
                }
            }
        }else{
            cid=0;
        }












        System.out.println("get in OrderServlet");
        /////* 获取套餐id和数量
        long foodID = 0;
        int foodNumbers = 0;
        //数量>0的套餐id的ArrayList
        ArrayList<Long> foodIDList = null;
        ArrayList<Integer> foodNumbersList = null;
        FoodsService fs = new FoodsService();
        ArrayList<Foods> foodsList = fs.getAllFoodsInfo();
        Foods foodIns = null;
        double totalPrice = 0;
        double priceOneFood = 0;
        for(int i=0;i<foodsList.size();i++){
            foodID = foodsList.get(i).getFoodid();//获取存在的food_id
            //获取存在的food_id的数量
            foodNumbers=  Integer.parseInt(request.getParameter(foodID+"number"));
            if(foodNumbers>0){
                /////* 如果这个订单的数量大于0,则查询该foodID,从Foods表中,获取该Foods实例.
                /////* 并将该food单价*这个数量,获取到单价,排列到每笔订单后面显示(如果有Ajax会更好)
                /////* 这里要做的就是[确认下单]-->显示每笔的价格,显示总价格-->[付款]
                /////* 而付款前的这些内容都还是在/foods中去回传显示.
                if(foodIDList==null){
                    foodIDList = new ArrayList<>();
                }
                if (foodNumbersList==null){
                    foodNumbersList = new ArrayList<>();
                }
                foodIDList.add(foodID);//食物ID列表
                foodNumbersList.add(foodNumbers);//食物数量列表
                //下这个单
                if(foodIns==null){
                    foodIns = new Foods();
                }
                //单价*数量
                foodIns = fs.getFoodsInsByID(foodID);
                priceOneFood = foodIns.getPrice()*foodNumbers;
                totalPrice += priceOneFood;
                //设置单种套餐*数量的价格到Attribute (其实在前端完全可以使用JS完成价格的计算并传到后端)
                request.setAttribute("price_"+foodID,priceOneFood);
            }
        }
        request.setAttribute("totalPrice",totalPrice);


        //创建订单
        Customers customer = null;
        CartsService cser = new CartsService();
        OrdersService os = new OrdersService();
        long[] orderIDAndOrderState;
        /////* 创建新购物车
        long cartID = 0;
        /////* 如果用户id存在并且用户选择套餐数量>0,则创建新购物车元组数据并返回long型cart_id
        if(cid>0 && foodIDList!=null){
            //查询cid的用户实例
            AuthService as = new AuthService();
            customer =  as.getCustomersInstanceByID(cid);
            cartID = cser.createNewCart(cid,foodIDList,foodNumbersList);
            request.setAttribute("foodIDList",foodIDList);
            request.setAttribute("foodNumbersList",foodNumbersList);
            if((cartID>0) && (customer!=null)){// 返回的购物车ID
                System.out.println("购物车ID:"+cartID);
                System.out.println("新购物车创建成功!");
                request.setAttribute("cartID",cartID);
                orderIDAndOrderState = new long[2];
                /////*创建1个购物车后创建这次的订单.订单状态为默认0(未付款)
                /////* 返回订单ID和订单状态(0)
                orderIDAndOrderState = os.createNewOrder(cartID,customer,totalPrice,0);
                if (orderIDAndOrderState[0]>0){
                    System.out.println("新建订单创建成功!订单号:"+orderIDAndOrderState[0]);
                    request.setAttribute("orderID",orderIDAndOrderState[0]);
                }
            }
        }else if(cid==0){
            request.setAttribute("cookieState","请重新登陆!");
        }else if(foodIDList==null || foodIDList.size()==0){
            request.setAttribute("orderHint","请选择您要购买的商品,再点击下单按钮.💗");
        }


        //跳转回foods展示要下单的食品总价
        request.getRequestDispatcher("/foods").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
