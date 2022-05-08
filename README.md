# 使用OKEX-API获取历史行情数据

描述：使用okex接口获取历史行情数据，支持程序中途退出之后断点继续，对请求自动重试。

## 配置要求： 
- java1.8 
- maven3
- mysql 5.6+

## 使用方法：
1. 使用mysql.sql创建两张table
2. 参考db.properties.demo创建db.properties文件
3. 参考okex.json.demo创建okex.json文件，去okex官网申请
4. 修改Starter.java配置，改为你想要的行情信息
5. 使用mvn编译整个项目
6. 运行


## 额外：
1. 需要在你的home路径创建logs文件夹用来保存日志
2. 交易对instId，需要自行去okex网站查询，常规交易对"BTC-USDT"，"ETH-USDT"已测试