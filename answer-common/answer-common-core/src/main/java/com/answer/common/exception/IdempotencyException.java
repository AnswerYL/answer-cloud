/**
 * @projectName answer-cloud
 * @package com.answer.common.exception
 * @className com.answer.common.exception.IdempotencyException
 */
package com.answer.common.exception;

/**
 * IdempotencyException
 * @description 幂等性异常
 * @author answer_wx
 * @date 2024/1/18 22:44
 * @version 1.0
 */
public class IdempotencyException extends RuntimeException{
    private static final long serialVersionUID = 6610083281801529147L;

    public IdempotencyException(String message) {
        super(message);
    }
}