package com.huajframe.seckill.exception;

import com.huajframe.seckill.enums.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 未登录错误
 *
 * @author Hua JFarmer
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoLoginException extends RuntimeException{

    /**
     * 返回前端信息集合
     */
    private RespBeanEnum respBeanEnum;
}
