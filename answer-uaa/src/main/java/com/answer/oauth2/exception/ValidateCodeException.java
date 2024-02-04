/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.exception
 * @className com.answer.oauth2.exception.ValidateCodeException
 */
package com.answer.oauth2.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * ValidateCodeException
 * @description 验证码异常
 * @author answer_wx
 * @date 2024/1/24 16:47
 * @version 1.0
 */
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}