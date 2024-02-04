/**
 * @projectName answer-cloud
 * @package com.answer.common.oss.properties
 * @className com.answer.common.oss.properties.FdfsProperties
 */
package com.answer.common.oss.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * FdfsProperties
 * @description FastDFS 配置
 * @author answer_wx
 * @date 2024/1/21 16:42
 * @version 1.0
 */
@Getter
@Setter
public class FdfsProperties {
    /**
     * fastdfs的http访问地址
     */
    private String webUrl;
}