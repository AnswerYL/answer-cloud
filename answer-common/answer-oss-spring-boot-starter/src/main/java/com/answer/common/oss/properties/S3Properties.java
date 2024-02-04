/**
 * @projectName answer-cloud
 * @package com.answer.common.oss.properties
 * @className com.answer.common.oss.properties.S3Properties
 */
package com.answer.common.oss.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * S3Properties
 * @description aws s3协议配置
 * @author answer_wx
 * @date 2024/1/21 16:45
 * @version 1.0
 */
@Setter
@Getter
public class S3Properties {
    /**
     * 用户名
     */
    private String accessKey;
    /**
     * 密码
     */
    private String accessKeySecret;
    /**
     * 访问端点
     */
    private String endpoint;
    /**
     * bucket名称
     */
    private String bucketName;
    /**
     * 区域
     */
    private String region;
    /**
     * path-style
     */
    private Boolean pathStyleAccessEnabled = true;
}