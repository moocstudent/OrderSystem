
--1.建立seq_foods序列用于food_id
create sequence seq_foods start with 101;
--2.建立foods表
create table Foods(
	food_id integer primary key default nextval('seq_foods'),
	food_name varchar(30) not null unique,
	food_brief varchar(300) not null,
	food_price numeric(10,2) default 0 not null check(food_price > 0)
);
--3.将序列归属为表的指定字段
alter sequence seq_foods owned by Foods.food_id;

--查看表foods
select * from foods;

--------------------------------------------------------------------------
--------------------------------------------------------------------------

--1.建立seq_customers序列用于Customers.customer_id
create sequence seq_customers start with 661001;
--2.建立Customers表
create table Customers(
	customer_id integer primary key default nextval('seq_customers'),
	customer_name varchar(55) not null unique,
	customer_password varchar(200) not null,
	customer_truename varchar(60) not null,
	customer_address varchar(300) not null,
	customer_phone numeric(11,0) check(length(cast(customer_phone as varchar))=11) not null,
	customer_balance decimal default 0 check(customer_balance >=0) not null
);
--3.将序列归属为表的指定字段
alter sequence seq_customers owned by Customers.customer_id;
									   
--查看Customers表
select * from customers;
drop table customers;
update Customers set customer_balance  = customer_balance + 1000
delete from Customers where customer_id  = 661002;
update nextval('seq_customers')
--------------------------------------------------------------------------
--------------------------------------------------------------------------
		
									   
--1.建立carts表(之前这个数据忘记保存,这点要记住啊!!!💗)
CREATE TABLE public.carts
(
    cart_id integer NOT NULL DEFAULT nextval('seq_carts'::regclass),
    customer_id integer,
    cart_contents character varying(600) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    CONSTRAINT carts_pkey PRIMARY KEY (cart_id),
    CONSTRAINT carts_customer_id_fkey FOREIGN KEY (customer_id)
        REFERENCES public.customers (customer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.carts
    OWNER to postgres;									   
--查询carts
select * from carts
delete from carts
---------------------------------------------------------------
---------------------------------------------------------------
select * from Orders o1,Carts c1 where order_id = 8810029 and o1.cart_id=c1.cart_id						   
select * from Orders o1,Carts c1,Customers cus1 where order_id = 8810029 and o1.cart_id=c1.cart_id and cus1.customer_id = o1.customer_id
select o1.order_id,c1.cart_id,c1.cart_contents,o1.total_price,cus1.customer_truename,cus1.customer_phone,cus1.customer_address,o1.order_date from Orders o1 left join Carts c1 on o1.cart_id = c1.cart_id left outer join Customers cus1 on cus1.customer_id = c1.customer_id and o1.order_state=1	
									   
									   

--1.建立seq_orders序列用于Orders.order_id
create sequence seq_orders start with 8810001;
--2.建立Orders表
create table Orders(
	order_id integer primary key default nextval('seq_orders'),
	cart_id integer not null,
	customer_id integer references Customers(customer_id),
	customer_address varchar(120) not null,
	customer_phone numeric(11,0) check(length(cast(customer_phone as varchar))=11) not null,
 	total_price decimal default 0 check(total_price >=0) not null,
	order_date date not null,
	order_state smallint default 0 								  
);
--3.将序列绑定到表的字段
alter sequence seq_orders owned by Orders.order_id;
delete from orders where order_state = 0
select * from Orders
drop sequence seq_orders
drop table orders
update Customers set customer_balance = customer_balance - (select total_price from Orders where order_id = 8810012 and cart_id = 10023 and order_state = 0 )			   