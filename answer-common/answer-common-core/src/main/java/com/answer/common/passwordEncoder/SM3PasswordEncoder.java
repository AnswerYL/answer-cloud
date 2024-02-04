/**
 * @projectName answer-cloud
 * @package com.answer.common.passwordEncoder
 * @className com.answer.common.passwordEncoder.SM3PasswordEncoder
 */
package com.answer.common.passwordEncoder;

import cn.hutool.crypto.SmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SM3PasswordEncoder
 * @description
 * @author answer_wx
 * @date 2024/1/18 19:13
 * @version 1.0
 */
@Slf4j
public class SM3PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return SmUtil.sm3(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        }
        if (encodedPassword == null || encodedPassword.length() == 0) {
            log.warn("Empty encoded password");
            return false;
        }
        String rawPasswordEncoded = this.encode(rawPassword.toString());
        return rawPasswordEncoded.equals(encodedPassword);
    }
}