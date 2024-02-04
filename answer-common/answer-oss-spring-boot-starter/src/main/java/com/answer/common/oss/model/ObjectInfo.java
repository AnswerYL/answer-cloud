/**
 * @projectName answer-cloud
 * @package com.answer.common.oss.model
 * @className com.answer.common.oss.model.ObjectInfo
 */
package com.answer.common.oss.model;

import lombok.Getter;
import lombok.Setter;

/**
 * ObjectInfo
 * @description 文件对象保存信息
 * @author answer_wx
 * @date 2024/1/21 16:46
 * @version 1.0
 */
@Getter
@Setter
public class ObjectInfo {
    /**
     * 对象查看路径
     */
    private String objectUrl;
    /**
     * 对象保存路径
     */
    private String objectPath;
}