package com.huajframe.seckill.config;

import com.huajframe.seckill.config.configbeans.LoginedInterceptor;
import com.huajframe.seckill.config.configbeans.NoLoginInterceptor;
import com.huajframe.seckill.config.configbeans.UserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * MVC配置类
 * @author Hua JFarmer
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private UserArgumentResolver userArgumentResolver;

    @Autowired
    private NoLoginInterceptor noLoginInterceptor;

    @Autowired
    private LoginedInterceptor loginedInterceptor;


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginedInterceptor)
                .addPathPatterns("/login/**");
        registry.addInterceptor(noLoginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login/**",
                        "/bootstrap/**",
                        "/jquery-validation/**",
                        "/js/**",
                        "/img/**",
                        "/layer/**");
    }
}
