package com.huajframe.seckill.util;

import java.util.UUID;

/**
 * 生成UUID的工具类，去除短横线-
 *
 * @author Hua JFarmer
 */
public class UUIDUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
