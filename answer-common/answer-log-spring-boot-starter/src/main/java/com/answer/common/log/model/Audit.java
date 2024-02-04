/**
 * @projectName answer-cloud
 * @package com.answer.log.model
 * @className com.answer.log.model.Audit
 */
package com.answer.common.log.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Audit
 * @description 审计日志实体类
 * @author answer_wx
 * @date 2024/1/19 11:23
 * @version 1.0
 */
@Getter
@Setter
public class Audit {
    /**
     * 操作时间
     */
    private LocalDateTime timestamp;
    /**
     * 应用名
     */
    private String applicationName;
    /**
     * 类名
     */
    private String className;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 租户id
     */
    private String clientId;
    /**
     * 操作信息
     */
    private String operation;
}