# 项目介绍
本项目名为秒杀商城，旨在综合学习秒杀(高并发)方面的知识

## 技术栈
Spring Boot, Redis, Mybaits-plus, MySQL, // TODO

测压工具：Jmeter

[测压效果](jmeter/README.md)

## 模块介绍
### 登录模块
1. 生成UUID存入cookie，根据uuid存储和获取redis的用户数据，实现分布式session的处理
2. 登录时两次md5加密，第一次前端盐值写死，完成第一次加密，第二次取出数据库中的盐值对第一次此
加密结果进行二次加密，再与数据库中的进行比较
3. 用拦截器进行判断，避免用户重复登录或未登录访问非登录接口

### 秒杀接口
判断秒杀商品是否超库存，用户是否重复提交订单，不是则生成订单和秒杀商品订单

