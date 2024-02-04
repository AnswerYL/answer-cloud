/**
 * @projectName answer-cloud
 * @package com.answer.log.properties
 * @className com.answer.log.properties.AuditLogProperties
 */
package com.answer.common.log.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * AuditLogProperties
 * @description 审计日志配置  运行时可刷新
 * @author answer_wx
 * @date 2024/1/19 11:18
 * @version 1.0
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "answer.audit-log")
@RefreshScope
public class AuditLogProperties {
    /**
     * 是否开启审计日志
     */
    private Boolean enabled = false;
    /**
     * 日志记录类型(logger/redis/db/es)
     */
    private String logType;
}