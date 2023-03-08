package com.huajframe.seckill.exception.handler;

import com.huajframe.seckill.exception.LoginedException;
import com.huajframe.seckill.exception.NoLoginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用来处理用户是否未登录访问
 * 是否重复登录
 *
 * @author Hua JFarmer
 */
@ControllerAdvice
public class LoginExceptionHandler {

    /**
     * 监听未登录和重复登录异常
     * @param e
     * @return
     */
    @ExceptionHandler({NoLoginException.class, LoginedException.class})
    public ModelAndView exceptionHandler(Exception e){
        ModelAndView modelAndView = new ModelAndView();
        if(e instanceof NoLoginException){
            NoLoginException ne = (NoLoginException) e;
            modelAndView.addObject("noLoginMessage", ne.getRespBeanEnum().getMessage());
            modelAndView.setViewName("login");
        }else{
            LoginedException loginedException = (LoginedException) e;
            // modelAndView.addObject("noLoginMessage", loginedException.getRespBeanEnum().getMessage());
            modelAndView.setViewName("goodsList");
        }
        return modelAndView;
    }
}
