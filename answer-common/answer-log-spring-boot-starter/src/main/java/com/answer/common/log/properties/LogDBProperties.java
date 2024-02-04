/**
 * @projectName answer-cloud
 * @package com.answer.log.properties
 * @className com.answer.log.properties.LogDBProperties
 */
package com.answer.common.log.properties;

import com.zaxxer.hikari.HikariConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * LogDBProperties
 * @description 日志数据源配置
 * logType=db时生效(非必须)，如果不配置则使用当前数据源
 * @author answer_wx
 * @date 2024/1/19 11:19
 * @version 1.0
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "answer.audit-log.datasource")
public class LogDBProperties extends HikariConfig {

}