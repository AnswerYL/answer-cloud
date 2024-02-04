/**
 * @projectName answer-cloud
 * @package com.answer.common.oauth2.properties
 * @className com.answer.common.oauth2.properties.TokenStoreProperties
 */
package com.answer.common.oauth2.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * TokenStoreProperties
 * @description token配置
 * @author answer_wx
 * @date 2024/1/19 18:52
 * @version 1.0
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "answer.oauth2.token.store")
@RefreshScope
public class TokenStoreProperties {
    /**
     * token存储类型(redis/db/authJwt/resJwt)
     */
    private String type = "redis";
}