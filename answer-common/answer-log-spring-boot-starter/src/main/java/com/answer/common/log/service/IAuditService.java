/**
 * @projectName answer-cloud
 * @package com.answer.log.service
 * @className com.answer.log.service.IAuditService
 */
package com.answer.common.log.service;

import com.answer.common.log.model.Audit;

/**
 * IAuditService
 * @description 审计日志接口
 * @author answer_wx
 * @date 2024/1/19 11:22
 * @version 1.0
 */
public interface IAuditService {
    void save(Audit audit);
}