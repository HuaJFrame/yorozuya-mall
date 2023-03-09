package com.huajframe.seckill.controller;

import com.huajframe.seckill.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private IGoodsService goodsService;

    /**
     * 跳转商品列表页面
     *
     * @param model
     * //@param user 根据cookies获取到的redis中的用户信息
     * @return
     */
    @RequestMapping("/toList")
    public String toList(Model model){
        // model.addAttribute("user", user);
        model.addAttribute("goodsList", goodsService.findGoodsVo());
        return "goodsList";
    }

    /**
     * 返回秒杀商品的详情
     * @param model
     * @param goodsId
     * @return
     */
    @GetMapping("/toDetail/{goodsId}")
    public String toDetail(Model model,
                           @PathVariable Long goodsId) {
        model.addAttribute("goods", goodsService.findGoodsVoByGoodsId(goodsId));
        return "goodsDetail";
    }
}
