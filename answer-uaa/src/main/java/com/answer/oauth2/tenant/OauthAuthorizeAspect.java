/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.tenant
 * @className com.answer.oauth2.tenant.OauthAuthorizeAspect
 */
package com.answer.oauth2.tenant;

import com.answer.common.context.TenantContextHolder;
import com.answer.common.model.LoginAppUser;
import com.answer.common.oauth2.token.TenantUsernamePasswordAuthenticationToken;
import com.answer.oauth2.service.impl.UserDetailServiceFactory;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Map;

/**
 * OauthAuthorizeAspect
 * @description /oauth/authorize拦截器
 * 解决不同租户单点登录时角色没变化
 * @author answer_wx
 * @date 2024/1/24 17:02
 * @version 1.0
 */
@Slf4j
@Component
@Aspect
public class OauthAuthorizeAspect {

    private final UserDetailServiceFactory userDetailsServiceFactory;

    public OauthAuthorizeAspect(UserDetailServiceFactory userDetailsServiceFactory) {
        this.userDetailsServiceFactory = userDetailsServiceFactory;
    }

    @Around("execution(* org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint.authorize(..))")
    public Object doAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Map<String, String> parameters = (Map<String, String>) args[1];
        Principal principal = (Principal) args[3];
        if (principal instanceof TenantUsernamePasswordAuthenticationToken) {
            TenantUsernamePasswordAuthenticationToken tenantToken = (TenantUsernamePasswordAuthenticationToken)principal;
            String clientId = tenantToken.getClientId();
            String requestClientId = parameters.get(OAuth2Utils.CLIENT_ID);
            //判断是否不同租户单点登录
            if (!requestClientId.equals(clientId)) {
                Object details = tenantToken.getDetails();
                try {
                    TenantContextHolder.setTenant(requestClientId);
                    //重新查询对应该租户的角色等信息
                    LoginAppUser user = (LoginAppUser)userDetailsServiceFactory.getService(tenantToken)
                            .loadUserByUsername(tenantToken.getName());
                    tenantToken = new TenantUsernamePasswordAuthenticationToken(user, tenantToken.getCredentials(), user.getAuthorities(), requestClientId);
                    tenantToken.setDetails(details);
                    args[3] = tenantToken;
                } finally {
                    TenantContextHolder.clear();
                }
            }
        }
        return joinPoint.proceed(args);
    }
}