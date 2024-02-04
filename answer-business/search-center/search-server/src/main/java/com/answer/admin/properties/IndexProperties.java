/**
 * @projectName answer-cloud
 * @package com.answer.admin.properties
 * @className com.answer.admin.properties.IndexProperties
 */
package com.answer.admin.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * IndexProperties
 * @description 索引配置
 * @author answer_wx
 * @date 2024/1/23 20:26
 * @version 1.0
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "answer.indices")
@RefreshScope
public class IndexProperties {
    /**
     * 配置过滤的索引名：默认只显示这些索引
     */
    private String show;
}