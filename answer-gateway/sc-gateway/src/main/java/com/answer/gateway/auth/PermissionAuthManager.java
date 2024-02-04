/**
 * @projectName answer-cloud
 * @package com.answer.gateway.auth
 * @className com.answer.gateway.auth.PermissionAuthManager
 */
package com.answer.gateway.auth;

import com.answer.common.model.SysMenu;
import com.answer.common.oauth2.service.impl.DefaultPermissionServiceImpl;
import com.answer.gateway.feign.AsycMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

/**
 * PermissionAuthManager
 * @description url权限认证
 * @author answer_wx
 * @date 2024/1/21 18:48
 * @version 1.0
 */
@Slf4j
@Component
public class PermissionAuthManager extends DefaultPermissionServiceImpl implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Resource
    private AsycMenuService asycMenuService;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {
        return authentication.map(auth -> {
            ServerWebExchange exchange = authorizationContext.getExchange();
            ServerHttpRequest request = exchange.getRequest();
            boolean isPermission = super.hasPermission(auth, request.getMethodValue(), request.getURI().getPath());
            return new AuthorizationDecision(isPermission);
        }).defaultIfEmpty(new AuthorizationDecision(false));
    }

    @Override
    public List<SysMenu> findMenuByRoleCodes(String roleCodes) {
        Future<List<SysMenu>> futureResult = asycMenuService.findByRoleCodes(roleCodes);
        try {
            return futureResult.get();
        } catch (Exception e) {
            log.error("asynMenuService.findMenuByRoleCodes-error", e);
        }
        return Collections.emptyList();
    }
}