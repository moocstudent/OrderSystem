AuthDao中暂时只有Customers的登陆注册.
如果要把商户的加进来就很繁琐了
应该重命名为各自的名称+AuthDao

AutoDao作为用户的信息有效性验证,(注册,登陆)的Dao层

MerchantersDao作为商家的信息有效性验证,(注册,登陆)的Dao层.