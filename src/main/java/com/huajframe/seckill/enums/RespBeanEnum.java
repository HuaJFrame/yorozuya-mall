package com.huajframe.seckill.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回状态枚举
 * @author Hua JFarmer
 */
@Getter
@AllArgsConstructor
public enum RespBeanEnum {
    //通用状态码
    SUCCESS(200,"success"),
    ERROR(500,"服务端异常"),
    //登录模块
    LOGIN_ERROR(500210,"用户名或者密码错误"),
    MOBILE_ERROR(500211,"手机号码格式错误"),
    BIND_ERROR(500212, "参数校验错"),
    UNLOGIN(500213, "暂时无法访问, 请先登录"),
    // LOGINED(500214, "亲，已经登录过了啊！"),
    SESSION_ERROR(500215,"session不存在或者已经失效");


    private final Integer code;
    private final String message;
}
