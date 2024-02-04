/**
 * @projectName answer-cloud
 * @package com.answer.common.oauth2.store
 * @className com.answer.common.oauth2.store.AuthRedisTokenStore
 */
package com.answer.common.oauth2.store;

import com.answer.common.oauth2.properties.SecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * AuthRedisTokenStore
 * @description 认证服务器使用Redis存取令牌
 * 需配置Redis参数
 * @author answer_wx
 * @date 2024/1/19 19:14
 * @version 1.0
 */
@Configuration
@ConditionalOnProperty(prefix = "answer.oauth2.token.store", name = "type", havingValue = "redis", matchIfMissing = true)
public class AuthRedisTokenStore {
    @Bean
    public TokenStore tokenStore(RedisConnectionFactory connectionFactory, SecurityProperties securityProperties, RedisSerializer<Object> redisValueSerializer) {
        return new CustomRedisTokenStore(connectionFactory, securityProperties, redisValueSerializer);
    }
}