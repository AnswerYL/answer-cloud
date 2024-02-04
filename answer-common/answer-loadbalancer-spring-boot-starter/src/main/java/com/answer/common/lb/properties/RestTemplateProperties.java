/**
 * @projectName answer-cloud
 * @package com.answer.common.lb.properties
 * @className com.answer.common.lb.properties.RestTemplateProperties
 */
package com.answer.common.lb.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * RestTemplateProperties
 * @description RestTemplate 配置
 * @author answer_wx
 * @date 2024/1/19 19:33
 * @version 1.0
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "answer.rest-template")
public class RestTemplateProperties {
    /**
     * 最大链接数
     */
    private int maxTotal = 200;
    /**
     * 同路由最大并发数
     */
    private int maxPerRoute = 50;
    /**
     * 读取超时时间 ms
     */
    private int readTimeout = 35000;
    /**
     * 链接超时时间 ms
     */
    private int connectTimeout = 10000;
}