/**
 * @projectName answer-cloud
 * @package com.answer.common.config
 * @className com.answer.common.config.DefaultWebMvcConfig
 */
package com.answer.common.config;

import com.answer.common.feign.UserService;
import com.answer.common.resolver.ClientArgumentResolver;
import com.answer.common.resolver.TokenArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * DefaultWebMvcConfig
 * @description 默认SpringMVC拦截器
 * @author answer_wx
 * @date 2024/1/18 19:16
 * @version 1.0
 */
public class DefaultWebMvcConfig implements WebMvcConfigurer {

    @Lazy
    @Autowired
    private UserService userService;

    /**
     * Token参数解析
     *
     * @param argumentResolvers 解析类
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //注入用户信息
        argumentResolvers.add(new TokenArgumentResolver(userService));
        //注入应用信息
        argumentResolvers.add(new ClientArgumentResolver());
    }
}