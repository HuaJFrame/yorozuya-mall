package com.huajframe.seckill.service;

import com.huajframe.seckill.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huajframe.seckill.vo.LoginVo;
import com.huajframe.seckill.vo.RespBean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hua JFrame
 * @since 2023-03-01
 */
public interface IUserService extends IService<User> {

    /**
     * 登录
     *
     * @param request
     * @param response
     * @param loginVo 前端传入
     * @return 登录结果
     */
    RespBean login(HttpServletRequest request,
                     HttpServletResponse response,
                     LoginVo loginVo);

    /**
     * 根据cookie在redis中取出用户信息
     * @param request
     * @param response
     * @return 用户信息
     */
    User getUserByCookie(HttpServletRequest request, HttpServletResponse response);
}
