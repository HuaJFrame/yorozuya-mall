package com.huajframe.seckill.config.configbeans;

import com.huajframe.seckill.entity.User;
import com.huajframe.seckill.enums.RespBeanEnum;
import com.huajframe.seckill.exception.LoginedException;
import com.huajframe.seckill.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 避免用户重复登录的拦截器
 * 导致redis中数据不正确
 *
 * @author Hua JFarmer
 */
@Component
public class LoginedInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = userService.getUserByCookie(request, response);
        //用户未登录允许访问，已经登录禁止访问
        if(user != null){
            throw new LoginedException(RespBeanEnum.LOGINED);
        }
        return true;
    }
}
