/**
 * @projectName answer-cloud
 * @package com.answer.common.oss
 * @className com.answer.common.oss.OssAutoConfigure
 */
package com.answer.common.oss;

import com.answer.common.oss.properties.FileServerProperties;
import com.answer.common.oss.template.FdfsTemplate;
import com.answer.common.oss.template.S3Template;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * OssAutoConfigure
 * @description OSS自动配置
 * @author answer_wx
 * @date 2024/1/21 16:56
 * @version 1.0
 */
@EnableConfigurationProperties({FileServerProperties.class})
@Import({FdfsTemplate.class, S3Template.class})
public class OssAutoConfigure {
}