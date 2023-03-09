package com.huajframe.seckill.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huajframe.seckill.entity.Order;
import com.huajframe.seckill.entity.SeckillOrder;
import com.huajframe.seckill.entity.User;
import com.huajframe.seckill.enums.RespBeanEnum;
import com.huajframe.seckill.service.IGoodsService;
import com.huajframe.seckill.service.IOrderService;
import com.huajframe.seckill.service.ISeckillOrderService;
import com.huajframe.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/seckill")
public class SeckillGoodsController {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ISeckillOrderService seckillOrderService;
    @Autowired
    private IOrderService orderService;

    /**
     * 执行秒杀
     *
     * @param model
     * @param user cookie中的用户信息
     * @param goodsId 商品id
     * @return 订单详细页面
     */
    @RequestMapping("/doSeckill")
    public String doSeckill(Model model, User user, Long goodsId) {
        model.addAttribute("user", user);

        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        //判断库存
        if (goodsVo.getStockCount() < 1) {
            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());
            return "seckillFail";
        }
        //判断是否重复抢购
        SeckillOrder seckillOrder = seckillOrderService.getOne(
                new QueryWrapper<SeckillOrder>()
                        .eq("user_id", user.getId())
                        .eq("goods_id", goodsId)
        );
        if (seckillOrder != null) {
            model.addAttribute("errmsg", RespBeanEnum.REPEATE_ERROR.getMessage());
            return "seckillFail";
        }
        Order order = orderService.seckill(user, goodsVo);
        model.addAttribute("order", order);
        model.addAttribute("goods",goodsVo);
        return "orderDetail";
    }

}
