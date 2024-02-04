/**
 * @projectName answer-cloud
 * @package com.answer.common.oss.properties
 * @className com.answer.common.oss.properties.FileServerProperties
 */
package com.answer.common.oss.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * FileServerProperties
 * @description File服务器配置
 * @author answer_wx
 * @date 2024/1/21 16:43
 * @version 1.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = FileServerProperties.PREFIX)
public class FileServerProperties {
    public static final String PREFIX = "answer.file-server";
    public static final String TYPE_FDFS = "fastdfs";
    public static final String TYPE_S3 = "s3";

    /**
     * 为以下2个值，指定不同的自动化配置
     * s3：aws s3协议的存储（七牛oss、阿里云oss、minio等）
     * fastdfs：本地部署的fastDFS
     */
    private String type;

    /**
     * aws s3配置
     */
    S3Properties s3 = new S3Properties();

    /**
     * fastDFS配置
     */
    FdfsProperties fdfs = new FdfsProperties();
}