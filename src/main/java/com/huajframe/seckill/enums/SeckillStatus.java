package com.huajframe.seckill.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 秒杀状态枚举类
 * @author Hua JFarmer
 */
@Getter
@AllArgsConstructor
public enum SeckillStatus {
    /**
     * 秒杀还未开始
     */
    SECKILL_UNSTART(0),
    /**
     * 秒杀进行中
     */
    SECKILL_DOING(1),
    /**
     * 秒杀已结束
     */
    SECKILL_END(2);

    private final int status;
}
