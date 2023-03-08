package com.huajframe.seckill.controller;

import com.huajframe.seckill.service.IUserService;
import com.huajframe.seckill.vo.LoginVo;
import com.huajframe.seckill.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 登录controller
 *
 * @author Hua JFarmer
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private IUserService userService;

    /**
     * 跳转到登录页
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    /**
     * 实现登录
     *
     * @param request
     * @param response
     * @param loginVo 前端传入
     * @return 登录成功结果
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(HttpServletRequest request,
                            HttpServletResponse response,
                            @Valid LoginVo loginVo){
        log.info(loginVo.toString());
        return userService.login(request, response, loginVo);
    }
}
