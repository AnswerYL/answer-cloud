/**
 * @projectName answer-cloud
 * @package com.answer.gateway.filter
 * @className com.answer.gateway.filter.TraceFilter
 */
package com.answer.gateway.filter;

import com.answer.common.log.properties.TraceProperties;
import com.answer.common.log.trace.MDCTraceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * TraceFilter
 * @description 生成日志追踪链路id，并放入header中
 * @author answer_wx
 * @date 2024/1/22 18:26
 * @version 1.0
 */
@Component
public class TraceFilter implements GlobalFilter, Ordered {
    @Autowired
    private TraceProperties traceProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (traceProperties.getEnable()) {
            //链路追踪id
            MDCTraceUtils.addTrace();

            ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate()
                    .headers(h -> {
                        h.add(MDCTraceUtils.TRACE_ID_HEADER, MDCTraceUtils.getTraceId());
                        h.add(MDCTraceUtils.SPAN_ID_HEADER, MDCTraceUtils.getNextSpanId());
                    })
                    .build();

            ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();
            return chain.filter(build);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}