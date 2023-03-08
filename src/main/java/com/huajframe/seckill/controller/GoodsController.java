package com.huajframe.seckill.controller;

import com.huajframe.seckill.entity.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hua JFrame
 * @since 2023-03-07
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    /**
     * 页面跳转
     *
     * @param model
     * @param user 根据cookies获取到的redis中的用户信息
     * @return
     */
    @RequestMapping("/toList")
    public String toList(Model model, User user){
        model.addAttribute("user", user);
        return "goodsList";
    }
}
