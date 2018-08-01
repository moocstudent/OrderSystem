package com.ykmimi.order.servlet;

import com.ykmimi.order.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ChangeDataServlet")
public class ChangeDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /////*字符编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession(false);
        long cid = 0;
        if (session!=null){
            cid = (long)session.getAttribute("cid");
        }
        /////* 获取用户更新的数据
        String truename = "";
        String address = "";
        String phoneStr = "";
        truename = request.getParameter("change_truename");
        address = request.getParameter("change_address");
        phoneStr = request.getParameter("change_phone");
        System.out.println(truename);
        System.out.println(address);
        System.out.println(phoneStr);

        AuthService as = null;
        ArrayList<Object> ChangeDataArray = new ArrayList();
        int dataChangeCount = 0;
        /////*其中有一项不为空的,则进行用户信息更新
        if((!(truename.length()==0) || !(address.length()==0)|| !(phoneStr.length()==0))){
            as = new AuthService();

        }

        if(!(truename.equals(""))){
            //向更新数据的数组里添加truename字符串
            ChangeDataArray.add(truename);
            System.out.println("向ChangeDataArray中添加了truename");
            dataChangeCount++;
        }else{
            //如果truename是不需要更改的,添加0到Array0位置
            ChangeDataArray.add("0");
        }
        if(!(address.equals(""))){
            //向更新数据的数组里添加address字符串
            ChangeDataArray.add(address);
            System.out.println("向ChangeDataArray中添加了address");
            dataChangeCount++;
        }else{
            //如果address是不需要更改的,添加0到Array1位置
            ChangeDataArray.add("0");
        }
        if(!(phoneStr.equals(""))){
            //向更新数据的数组里添加phone字符串
            long phone = Long.parseLong(phoneStr);
            ChangeDataArray.add(phoneStr);
            System.out.println("向ChangeDataArray中添加了phone");
            dataChangeCount++;
//            changeState = as.changeUserInfoByInputData();
//            if (changeState==1) {
//                System.out.println("更新用户电话");
//            }



        }else{
            //如果phone是不需要更改的,添加0到Array2位置
            ChangeDataArray.add("0");
            System.out.println("没有phone,添加了0");
        }
        /////* 如果更改的用户数据不为空,则说明至少改了其中一项,那么进行Service传递该Array
        if(dataChangeCount!=0){
            request.setAttribute("updateOK","更新数据成功!");
            System.out.println("数据计数不为0");
            //返回的数组中,下标0为truename,下标1为address,下标2为phone
            int changeState[] = null;
            try {
                changeState = as.changeUserInfoByInputData(cid, ChangeDataArray);
            }catch (Exception e){
                e.printStackTrace();
            }
            if(changeState[0]!=0 && changeState[0]==1){
                //实名更新成功
                request.setAttribute("trueNameOK","用户实名更新成功.");
                System.out.println("用户实名更新成功");
            }
            if(changeState[1]!=0 && changeState[1]==1){
                //地址更新成功
                request.setAttribute("addressOK","收货地址更新成功.");
                System.out.println("收货地址更新成功");
            }
            if(changeState[2]!=0 && changeState[2]==1){
                //电话更新成功
                request.setAttribute("phoneOK","电话号码更新成功.");
                System.out.println("电话号码更新成功");
            }
            request.getRequestDispatcher("/showUser").forward(request,response);
        }else{
            request.setAttribute("noChange","数据无更新.");
            System.out.println("数据无更新");
            request.getRequestDispatcher("/userInfo.jsp").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
