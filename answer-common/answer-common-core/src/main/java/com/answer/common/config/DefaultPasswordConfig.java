/**
 * @projectName answer-cloud
 * @package com.answer.common.config
 * @className com.answer.common.config.DefaultPasswordConfig
 */
package com.answer.common.config;

import com.answer.common.utils.PwdEncoderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * DefaultPasswordConfig
 * @description 密码加密配置类
 * @author answer_wx
 * @date 2024/1/18 19:11
 * @version 1.0
 */
public class DefaultPasswordConfig {
    @Bean
    @ConditionalOnMissingBean
    public PasswordEncoder passwordEncoder() {
        return PwdEncoderUtil.getDelegatingPasswordEncoder("bcrypt");
    }
}