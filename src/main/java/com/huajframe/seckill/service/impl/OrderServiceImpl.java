package com.huajframe.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huajframe.seckill.entity.Order;
import com.huajframe.seckill.dao.OrderMapper;
import com.huajframe.seckill.entity.SeckillGoods;
import com.huajframe.seckill.entity.SeckillOrder;
import com.huajframe.seckill.entity.User;
import com.huajframe.seckill.enums.OrderStatusEnum;
import com.huajframe.seckill.service.IGoodsService;
import com.huajframe.seckill.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huajframe.seckill.service.ISeckillGoodsService;
import com.huajframe.seckill.service.ISeckillOrderService;
import com.huajframe.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hua JFrame
 * @since 2023-03-07
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private ISeckillGoodsService seckillGoodsService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ISeckillOrderService seckillOrderService;

    /**
     * 秒杀订单
     *
     * @param user 用户信息
     * @param goods 商品信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order seckill(User user, GoodsVo goods) {
        //秒杀商品表减库存
        SeckillGoods seckillGoods = seckillGoodsService.getOne(
                new QueryWrapper<SeckillGoods>()
                        .eq("goods_id", goods.getId())
        );
        seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);
        seckillGoodsService.updateById(seckillGoods);
        //生成订单
        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goods.getId());
        order.setDeliverAddrId(0L);
        order.setGoodsName(goods.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(seckillGoods.getSeckillPrice());
        order.setOrderChannel(1);
        order.setStatus(OrderStatusEnum.UNPAY.getStatus());
        order.setCreateDate(new Date());
        orderMapper.insert(order);
        //生成秒杀订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setUserId(user.getId());
        seckillOrder.setGoodsId(goods.getId());
        seckillOrderService.save(seckillOrder);
        return order;
    }
}
