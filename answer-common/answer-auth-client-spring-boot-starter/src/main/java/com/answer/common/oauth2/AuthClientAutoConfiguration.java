/**
 * @projectName answer-cloud
 * @package com.answer.common.oauth2
 * @className com.answer.common.oauth2.AuthClientAutoConfiguration
 */
package com.answer.common.oauth2;

import com.answer.common.oauth2.properties.SecurityProperties;
import com.answer.common.oauth2.properties.TokenStoreProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * AuthClientAutoConfiguration
 * @description 鉴权自动配置
 * @author answer_wx
 * @date 2024/1/19 19:23
 * @version 1.0
 */
@EnableConfigurationProperties({SecurityProperties.class, TokenStoreProperties.class})
@ComponentScan
public class AuthClientAutoConfiguration {
}