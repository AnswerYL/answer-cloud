/**
 * @projectName answer-cloud
 * @package com.answer.log.model
 * @className com.answer.log.model.TraceLog
 */
package com.answer.log.model;

import lombok.Getter;
import lombok.Setter;

/**
 * TraceLog
 * @description 日志链路对象
 * @author answer_wx
 * @date 2024/1/24 16:00
 * @version 1.0
 */
@Getter
@Setter
public class TraceLog {
    private String spanId;
    private String parentId;
    private String appName;
    private String serverIp;
    private String serverPort;
}