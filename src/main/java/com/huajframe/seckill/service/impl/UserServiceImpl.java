package com.huajframe.seckill.service.impl;

import com.huajframe.seckill.entity.User;
import com.huajframe.seckill.dao.UserMapper;
import com.huajframe.seckill.enums.RespBeanEnum;
import com.huajframe.seckill.exception.GlobalException;
import com.huajframe.seckill.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huajframe.seckill.util.CookieUtil;
import com.huajframe.seckill.util.MD5Util;
import com.huajframe.seckill.util.UUIDUtil;
import com.huajframe.seckill.util.ValidatorUtil;
import com.huajframe.seckill.vo.LoginVo;
import com.huajframe.seckill.vo.RespBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hua JFrame
 * @since 2023-03-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 登录
     *
     * @param request
     * @param response
     * @param loginVo  前端传入
     * @return 登录结果
     */
    @Override
    public RespBean login(HttpServletRequest request, HttpServletResponse response, LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //根据电话号码查询用户
        User user = userMapper.selectById(mobile);
        //用户为空
        if(user == null){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        //校验密码，根据前端传入的fromPass和数据库的盐生成的密码并和实际密码比对
        String expPassToDBPass = MD5Util.formPassToDBPass(password, user.getSalt());
        if (!expPassToDBPass.equals(user.getPassword())){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        // 生成cookie
        String token = UUIDUtil.uuid();
        //存入redis
        redisTemplate.opsForValue().set("user:" + token, user);
        //设置cookie
        CookieUtil.setCookie(request, response, "userToken", token);
        return RespBean.success(token);
    }

    /**
     * 根据cookie在redis中取出用户信息
     *
     * @param request
     * @param response
     * @return 用户信息
     */
    @Override
    public User getUserByCookie(HttpServletRequest request, HttpServletResponse response) {
        String token = CookieUtil.getCookieValue(request, "userToken");
        if(StringUtils.isEmpty(token)){
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user:" + token);
        if(user != null){
            //重新设置一下cookie
            CookieUtil.setCookie(request, response, "userToken", token);
        }
        return user;
    }
}
