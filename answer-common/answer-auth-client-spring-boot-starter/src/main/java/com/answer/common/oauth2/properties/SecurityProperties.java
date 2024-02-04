/**
 * @projectName answer-cloud
 * @package com.answer.common.oauth2.properties
 * @className com.answer.common.oauth2.properties.SecurityProperties
 */
package com.answer.common.oauth2.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * SecurityProperties
 * @description
 * @author answer_wx
 * @date 2024/1/19 17:58
 * @version 1.0
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "answer.security")
@RefreshScope
public class SecurityProperties {
    private AuthProperties auth = new AuthProperties();

    private PermitProperties ignore = new PermitProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();
}