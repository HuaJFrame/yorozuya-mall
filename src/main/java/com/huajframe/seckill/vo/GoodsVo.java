package com.huajframe.seckill.vo;

import com.huajframe.seckill.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品的返回对象
 *
 * @author Hua JFarmer
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo extends Goods {
    /**
     * 秒杀价格
     */
    private BigDecimal seckillPrice;
    /**
     * 秒杀库存数量
     */
    private Integer stockCount;
    /**
     * 秒杀开始时间
     */
    private Date startDate;
    /**
     * 秒杀结束时间
     */
    private Date endDate;
    /**
     * 秒杀状态
     */
    private int secKillStatus;
    /**
     * 秒杀开始时间
     */
    private int remainSeconds;
}
