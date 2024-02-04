/**
 * @projectName answer-cloud
 * @package com.answer.common.exception
 * @className com.answer.common.exception.BusinessException
 */
package com.answer.common.exception;

/**
 * BusinessException
 * @description 统一异常处理 -- 业务异常
 * @author answer_wx
 * @date 2024/1/18 22:34
 * @version 1.0
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 6610083281801529147L;

    public BusinessException(String message) {
        super(message);
    }
}