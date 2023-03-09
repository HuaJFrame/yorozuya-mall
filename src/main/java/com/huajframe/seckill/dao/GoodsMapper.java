package com.huajframe.seckill.dao;

import com.huajframe.seckill.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huajframe.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Hua JFrame
 * @since 2023-03-07
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     * 获取商品列表
     *
     *
     * @return 商品列表
     */
    List<GoodsVo> findGoodsVo();

    /**
     * 根据商品id获取商品详情
     *
     *
     * @param goodsId 商品id
     * @return
     */
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
