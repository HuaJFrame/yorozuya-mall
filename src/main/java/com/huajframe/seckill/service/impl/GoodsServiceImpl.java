package com.huajframe.seckill.service.impl;

import com.huajframe.seckill.entity.Goods;
import com.huajframe.seckill.dao.GoodsMapper;
import com.huajframe.seckill.enums.SeckillStatus;
import com.huajframe.seckill.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huajframe.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hua JFrame
 * @since 2023-03-07
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 获取商品列表
     *
     * @return 商品列表
     */
    @Override
    public List<GoodsVo> findGoodsVo() {
        return goodsMapper.findGoodsVo();
    }

    /**
     * 根据商品id获取商品详情
     *
     * @param goodsId 商品id
     * @return 某个商品的详细信息
     */
    @Override
    public GoodsVo findGoodsVoByGoodsId(Long goodsId) {
        GoodsVo goodsVo = goodsMapper.findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        //当前时间
        Date date = new Date();
        //秒杀状态
        int secKillStatus = 0;
        //剩余时间
        int remainSeconds = 0;
        if(date.before(startDate)){
            //秒杀还未开始
            remainSeconds = (int) ((startDate.getTime()- date.getTime())/1000);
        }else if (date.after(endDate)) {
            secKillStatus = SeckillStatus.SECKILL_END.getStatus();
            remainSeconds = -1;
        }else{
            //秒杀进行中
            secKillStatus = SeckillStatus.SECKILL_DOING.getStatus();
        }
        goodsVo.setSecKillStatus(secKillStatus);
        goodsVo.setRemainSeconds(remainSeconds);
        return goodsVo;
    }
}
