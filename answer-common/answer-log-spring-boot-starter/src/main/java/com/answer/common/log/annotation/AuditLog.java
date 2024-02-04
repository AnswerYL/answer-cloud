/**
 * @projectName answer-cloud
 * @package com.answer.log.annotation
 * @className com.answer.log.annotation.AuditLog
 */
package com.answer.common.log.annotation;

import java.lang.annotation.*;

/**
 * AuditLog
 * @description 审计日志注解
 * @author answer_wx
 * @date 2024/1/19 11:12
 * @version 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuditLog {
    /**
     * 操作信息
     */
    String operation();
}