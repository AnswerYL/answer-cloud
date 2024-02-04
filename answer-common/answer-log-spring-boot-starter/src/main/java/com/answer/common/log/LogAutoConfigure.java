/**
 * @projectName answer-cloud
 * @package com.answer.log.config
 * @className com.answer.log.config.LogAutoConfigure
 */
package com.answer.common.log;

import com.answer.common.log.properties.AuditLogProperties;
import com.answer.common.log.properties.LogDBProperties;
import com.answer.common.log.properties.TraceProperties;
import com.zaxxer.hikari.HikariConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * LogAutoConfigure
 * @description 日志自动配置
 * @author answer_wx
 * @date 2024/1/19 11:59
 * @version 1.0
 */
@ComponentScan
@EnableConfigurationProperties({TraceProperties.class, AuditLogProperties.class})
public class LogAutoConfigure {
    /**
     * 日志数据库配置
     */
    @Configuration
    @ConditionalOnClass(HikariConfig.class)
    @EnableConfigurationProperties(LogDBProperties.class)
    public static class LogDbAutoConfigure {}
}