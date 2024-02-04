/**
 * @projectName answer-cloud
 * @package com.answer.gateway.config
 * @className com.answer.gateway.config.CorsConfig
 */
package com.answer.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * CorsConfig
 * @description 跨域配置类
 * @author answer_wx
 * @date 2024/1/21 19:00
 * @version 1.0
 */
@Configuration
public class CorsConfig {
    private static final String ALL = "*";

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // cookie跨域
        config.setAllowCredentials(Boolean.TRUE);
        config.addAllowedMethod(ALL);
        config.addAllowedOriginPattern(ALL);
        config.addAllowedHeader(ALL);
        // 配置前端js允许访问的自定义响应头
        config.addExposedHeader("setToken");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}