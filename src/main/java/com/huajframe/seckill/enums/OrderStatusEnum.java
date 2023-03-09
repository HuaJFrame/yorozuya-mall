package com.huajframe.seckill.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态枚举
 *
 * @author Hua JFarmer
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    /**
     * 未支付
     */
    UNPAY(0),
    /**
     * 代发货
     */
    WAIT_DELIVERY(1),
    /**
     * 已发货
     */
    DELIVERYED(2),
    /**
     * 已收货
     */
    HARVESTED(3),
    /**
     * 已退款
     */
    REFUNDED(4),
    /**
     * 已完成
     */
    FINISHED(5);


    private Integer status;
}
