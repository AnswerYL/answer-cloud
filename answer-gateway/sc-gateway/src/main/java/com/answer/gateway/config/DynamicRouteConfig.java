/**
 * @projectName answer-cloud
 * @package com.answer.gateway.config
 * @className com.answer.gateway.config.DynamicRouteConfig
 */
package com.answer.gateway.config;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.answer.gateway.route.NacosRouteDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DynamicRouteConfig
 * @description 动态路由配置
 * @author answer_wx
 * @date 2024/1/21 19:01
 * @version 1.0
 */
@Configuration
@ConditionalOnProperty(prefix = "answer.gateway.dynamicRoute", name = "enabled", havingValue = "true")
public class DynamicRouteConfig {
    @Autowired
    private ApplicationEventPublisher publisher;

    /**
     * Nacos实现方式
     */
    @Configuration
    @ConditionalOnProperty(prefix = "answer.gateway.dynamicRoute", name = "dataType", havingValue = "nacos", matchIfMissing = true)
    public class NacosDynRoute {
        @Autowired
        private NacosConfigProperties nacosConfigProperties;

        @Bean
        public NacosRouteDefinitionRepository nacosRouteDefinitionRepository() {
            return new NacosRouteDefinitionRepository(publisher, nacosConfigProperties);
        }
    }
}