/**
 * @projectName answer-cloud
 * @package ui.properties
 * @className ui.properties.UiProperties
 */
package ui.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * UiProperties
 * @description 前端服务配置
 * @author answer_wx
 * @date 2024/1/24 18:14
 * @version 1.0
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "answer.ui")
@RefreshScope
public class UiProperties {
    private String pathContext = "/";
}