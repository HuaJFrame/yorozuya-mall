package com.huajframe.seckill.service;

import com.huajframe.seckill.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huajframe.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hua JFrame
 * @since 2023-03-07
 */
public interface IGoodsService extends IService<Goods> {
    /**
     * 获取商品列表
     *
     * @return 商品列表
     */
    List<GoodsVo> findGoodsVo();

    /**
     * 根据商品id获取商品详情
     * @param goodsId 商品id
     * @return 某个商品的详细信息
     */
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
