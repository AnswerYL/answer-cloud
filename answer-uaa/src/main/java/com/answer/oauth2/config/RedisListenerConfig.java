/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.config
 * @className com.answer.oauth2.config.RedisListenerConfig
 */
package com.answer.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * RedisListenerConfig
 * @description Redis过期监听配置类
 * @author answer_wx
 * @date 2024/1/24 16:50
 * @version 1.0
 */
@Configuration
public class RedisListenerConfig {
    @Bean
    @Primary
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory factory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        return container;
    }
}