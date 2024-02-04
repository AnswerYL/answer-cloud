/**
 * @projectName answer-cloud
 * @package com.answer.common.utils
 * @className com.answer.common.utils.LoginUserUtils
 */
package com.answer.common.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.answer.common.constant.SecurityConstants;
import com.answer.common.feign.UserService;
import com.answer.common.model.SysRole;
import com.answer.common.model.SysUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * LoginUserUtils
 * @description 获取当前登录人工具类
 * @author answer_wx
 * @date 2024/1/18 19:38
 * @version 1.0
 */
public class LoginUserUtil {
    /**
     * 获取当前登录人
     */
    public static SysUser getCurrentUser(HttpServletRequest request, boolean isFull) {
        SysUser sysUser = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = authentication.getPrincipal();
            //客户端模式只返回一个clientId
            if (principal instanceof SysUser) {
                sysUser = (SysUser) principal;
            }
        }
        if (sysUser == null) {
            String userId = request.getHeader(SecurityConstants.USER_ID_HEADER);
            String username = request.getHeader(SecurityConstants.USER_HEADER);
            String roles = request.getHeader(SecurityConstants.ROLE_HEADER);
            if (StrUtil.isAllNotBlank(username, userId)) {
                if (isFull) {
                    UserService userService = SpringUtil.getBean(UserService.class);
                    sysUser = userService.selectByUsername(username);
                } else {
                    sysUser = new SysUser();
                    sysUser.setId(Long.valueOf(userId));
                    sysUser.setUsername(username);
                }
                if (StrUtil.isNotBlank(roles)) {
                    List<SysRole> sysRoleList = new ArrayList<>();
                    Arrays.stream(roles.split(",")).forEach(role -> {
                        SysRole sysRole = new SysRole();
                        sysRole.setCode(role);
                        sysRoleList.add(sysRole);
                    });
                    sysUser.setRoles(sysRoleList);
                }
            }
        }
        return sysUser;
    }

    public static SysUser getCurrentUser(boolean isFull) {
        // 从请求上下文里获取 Request 对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest contextRequest = Objects.requireNonNull(requestAttributes).getRequest();
        return LoginUserUtil.getCurrentUser(contextRequest, isFull);
    }
}