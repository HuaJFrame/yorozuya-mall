package com.huajframe.seckill.config.configbeans;

import com.huajframe.seckill.entity.User;
import com.huajframe.seckill.enums.RespBeanEnum;
import com.huajframe.seckill.exception.NoLoginException;
import com.huajframe.seckill.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 未登录拦截器
 *
 * @author Hua JFarmer
 */
@Component
public class NoLoginInterceptor implements HandlerInterceptor {
    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = userService.getUserByCookie(request, response);
        //为空，就是没登陆
        if(user == null){
            throw new NoLoginException(RespBeanEnum.UNLOGIN);
        }
        return true;
    }
}
