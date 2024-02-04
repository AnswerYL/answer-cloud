/**
 * @projectName answer-cloud
 * @package com.answer.common.exception
 * @className com.answer.common.exception.LockException
 */
package com.answer.common.exception;

/**
 * LockException
 * @description 分布式锁异常
 * @author answer_wx
 * @date 2024/1/18 22:44
 * @version 1.0
 */
public class LockException extends RuntimeException {
    private static final long serialVersionUID = 6610083281801529147L;

    public LockException(String message) {
        super(message);
    }
}