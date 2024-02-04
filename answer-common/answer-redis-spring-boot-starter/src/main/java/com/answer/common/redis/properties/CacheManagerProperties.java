/**
 * @projectName answer-cloud
 * @package com.answer.redis.properties
 * @className com.answer.redis.properties.CacheManagerProperties
 */
package com.answer.common.redis.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * CacheManagerProperties
 * @description 缓存管理配置
 * @author answer_wx
 * @date 2024/1/19 17:30
 * @version 1.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "answer.cache-manager")
public class CacheManagerProperties {

    private List<CacheConfig> configs;

    @Setter
    @Getter
    public static class CacheConfig {
        /**
         * cache key
         */
        private String key;
        /**
         * 过期时间，sec
         */
        private long second = 60;
    }

}