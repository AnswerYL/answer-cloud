/**
 * @projectName answer-cloud
 * @package com.answer.common
 * @className com.answer.common.LoginUserFilter
 */
package com.answer.common.filter;

import com.answer.common.context.LoginUserContextHolder;
import com.answer.common.model.SysUser;
import com.answer.common.utils.LoginUserUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * LoginUserFilter
 * @description 获取当前登录人过滤器
 * @author answer_wx
 * @date 2024/1/19 10:35
 * @version 1.0
 */
@ConditionalOnClass(Filter.class)
@Order
public class LoginUserFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            SysUser user = LoginUserUtil.getCurrentUser(request, false);
            LoginUserContextHolder.setUser(user);
            filterChain.doFilter(request, response);
        } finally {
            LoginUserContextHolder.clear();
        }
    }
}