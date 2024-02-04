/**
 * @projectName answer-cloud
 * @package com.answer.gateway.auth
 * @className com.answer.gateway.auth.JsonAuthenticationEntryPoint
 */
package com.answer.gateway.auth;

import com.answer.common.utils.WebfluxResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * JsonAuthenticationEntryPoint
 * @description 401未授权异常处理，转换为JSON
 * @author answer_wx
 * @date 2024/1/21 18:45
 * @version 1.0
 */
@Slf4j
public class JsonAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {
    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        return WebfluxResponseUtil.responseFailed(exchange, HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }
}