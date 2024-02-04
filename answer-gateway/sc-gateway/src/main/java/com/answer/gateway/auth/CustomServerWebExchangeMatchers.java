/**
 * @projectName answer-cloud
 * @package com.answer.gateway.auth
 * @className com.answer.gateway.auth.CustomServerWebExchangeMatchers
 */
package com.answer.gateway.auth;

import com.answer.common.oauth2.properties.SecurityProperties;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * CustomServerWebExchangeMatchers
 * @description 自定义 ServerWebExchangeMatcher
 * 解决只要请求携带access_token，排除鉴权的url依然会被拦截
 * @author answer_wx
 * @date 2024/1/21 18:43
 * @version 1.0
 */
public class CustomServerWebExchangeMatchers implements ServerWebExchangeMatcher {
    private final SecurityProperties securityProperties;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public CustomServerWebExchangeMatchers(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public Mono<MatchResult> matches(ServerWebExchange exchange) {
        for (String url : securityProperties.getIgnore().getUrls()) {
            if (antPathMatcher.match(url, exchange.getRequest().getURI().getPath())) {
                return MatchResult.notMatch();
            }
        }
        return MatchResult.match();
    }
}