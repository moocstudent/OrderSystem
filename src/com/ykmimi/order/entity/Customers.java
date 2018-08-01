package com.ykmimi.order.entity;

public class Customers {
	
	private long customer_id;//用户id
	private String customer_name;//用户登陆名
	private String customer_password;//用户密码
	private String customer_truename;//用户实名
	private String customer_address;//用户地址
	private long customer_phone;//用户手机号
	private double customer_balance;//用户余额
	
	public Customers() {
		
	}
	
	
	/////* 
	
	
	/////* 获取数据后创建对象用的构造器
	public Customers(long customer_id, String customer_name, String customer_password, String customer_truename,
			String customer_address, long customer_phone, double customer_balance) {
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.customer_password = customer_password;
		this.customer_truename = customer_truename;
		this.customer_address = customer_address;
		this.customer_phone = customer_phone;
		this.customer_balance = customer_balance;
	}

	/////* 注册用户创建用户对象时使用
    public Customers(String customer_name, String customer_password, String customer_truename, String customer_address, long customer_phone) {
		this.customer_name = customer_name;
		this.customer_password = customer_password;
		this.customer_truename = customer_truename;
		this.customer_address = customer_address;
		this.customer_phone = customer_phone;
    }


    /////* getter & setter
	public long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_password() {
		return customer_password;
	}
	public void setCustomer_password(String customer_password) {
		this.customer_password = customer_password;
	}
	public String getCustomer_truename() {
		return customer_truename;
	}
	public void setCustomer_truename(String customer_truename) {
		this.customer_truename = customer_truename;
	}
	public String getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}
	public long getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(long customer_phone) {
		this.customer_phone = customer_phone;
	}
	public double getCustomer_balance() {
		return customer_balance;
	}
	public void setCustomer_balance(double customer_balance) {
		this.customer_balance = customer_balance;
	}
	
	
}
