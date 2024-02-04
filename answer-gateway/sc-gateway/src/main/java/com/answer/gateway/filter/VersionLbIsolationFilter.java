/**
 * @projectName answer-cloud
 * @package com.answer.gateway.filter
 * @className com.answer.gateway.filter.VersionLbIsolationFilter
 */
package com.answer.gateway.filter;

import com.answer.common.constant.CommonConstant;
import com.answer.common.constant.ConfigConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * VersionLbIsolationFilter
 * @description
 * @author answer_wx
 * @date 2024/1/22 18:30
 * @version 1.0
 */
@Component
public class VersionLbIsolationFilter implements GlobalFilter, Ordered {
    @Value("${" + ConfigConstants.CONFIG_LOADBALANCE_ISOLATION_ENABLE + ":}")
    private Boolean enableVersionControl;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (Boolean.TRUE.equals(enableVersionControl)
                && exchange.getRequest().getQueryParams().containsKey(CommonConstant.ANSWER_VERSION)) {
            String version = exchange.getRequest().getQueryParams().get(CommonConstant.ANSWER_VERSION).get(0);
            ServerHttpRequest rebuildRequest = exchange.getRequest().mutate().headers(header -> {
                header.add(CommonConstant.ANSWER_VERSION, version);
            }).build();
            ServerWebExchange rebuildServerWebExchange = exchange.mutate().request(rebuildRequest).build();
            return chain.filter(rebuildServerWebExchange);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}