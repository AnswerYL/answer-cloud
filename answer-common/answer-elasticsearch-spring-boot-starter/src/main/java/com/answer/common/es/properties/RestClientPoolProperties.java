/**
 * @projectName answer-cloud
 * @package com.answer.common.es.properties
 * @className com.answer.common.es.properties.RestClientPoolProperties
 */
package com.answer.common.es.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * RestClientPoolProperties
 * @description es的httpClient连接池配置
 * @author answer_wx
 * @date 2024/1/21 16:36
 * @version 1.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "answer.elasticsearch.rest-pool")
@RefreshScope
public class RestClientPoolProperties {
    /**
     * 链接建立超时时间
     */
    private Integer connectTimeOut = 1000;
    /**
     * 等待数据超时时间
     */
    private Integer socketTimeOut = 30000;
    /**
     * 连接池获取连接的超时时间
     */
    private Integer connectionRequestTimeOut = 500;
    /**
     * 最大连接数
     */
    private Integer maxConnectNum = 30;
    /**
     * 最大路由连接数
     */
    private Integer maxConnectPerRoute = 10;

}