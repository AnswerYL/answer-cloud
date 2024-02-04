/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.service.impl
 * @className com.answer.oauth2.service.impl.RedisAuthorizationCodeServices
 */
package com.answer.oauth2.service.impl;

import com.answer.common.redis.template.CustomRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * RedisAuthorizationCodeServices
 * @description JdbcAuthorizationCodeServices替换
 * @author answer_wx
 * @date 2024/1/24 16:37
 * @version 1.0
 */
@Service
public class RedisAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {

    private final CustomRedisTemplate redisTemplate;
    private final RedisSerializer<Object> valueSerializer;

    public RedisAuthorizationCodeServices(CustomRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueSerializer = RedisSerializer.java();
    }

    /**
     * 替换JdbcAuthorizationCodeServices的存储策略
     * 将存储code到redis，并设置过期时间，10分钟
     */
    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        redisTemplate.setExpire(redisKey(code), authentication, 10, TimeUnit.MINUTES, valueSerializer);
    }

    @Override
    protected OAuth2Authentication remove(final String code) {
        String codeKey = redisKey(code);
        OAuth2Authentication token = (OAuth2Authentication) redisTemplate.get(codeKey, valueSerializer);
        redisTemplate.del(codeKey);
        return token;
    }

    /**
     * redis中 code key的前缀
     *
     * @param code
     * @return
     */
    private String redisKey(String code) {
        return "oauth:code:" + code;
    }
}