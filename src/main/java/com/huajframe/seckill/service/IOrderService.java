package com.huajframe.seckill.service;

import com.huajframe.seckill.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huajframe.seckill.entity.User;
import com.huajframe.seckill.vo.GoodsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hua JFrame
 * @since 2023-03-07
 */
public interface IOrderService extends IService<Order> {
    /**
     * 秒杀订单
     * @param user
     * @param goods
     * @return
     */
    Order seckill(User user, GoodsVo goods);
}
