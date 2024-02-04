/**
 * @projectName answer-cloud
 * @package com.answer.log.properties
 * @className com.answer.log.properties.TraceProperties
 */
package com.answer.common.log.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * TraceProperties
 * @description 日志链路追踪配置 运行时可更新
 * @author answer_wx
 * @date 2024/1/19 11:20
 * @version 1.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "answer.trace")
@RefreshScope
public class TraceProperties {
    /**
     * 是否开启日志链路追踪
     */
    private Boolean enable = false;
}