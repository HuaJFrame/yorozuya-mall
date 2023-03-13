package com.huajframe.seckill.controller;


import com.huajframe.seckill.entity.User;
import com.huajframe.seckill.vo.RespBean;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hua JFrame
 * @since 2023-03-01
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 返回用户信息，用于JMeter测试
     *
     * @param user 用户信息
     * @return
     */
    @RequestMapping("/info")
    @ResponseBody
    public RespBean info(User user){
        return RespBean.success(user);
    }
}
