/**
 * @projectName answer-cloud
 * @package com.answer.log.service.impl
 * @className com.answer.log.service.impl.LoggerAuditServiceImpl
 */
package com.answer.common.log.service.impl;

import com.answer.common.log.model.Audit;
import com.answer.common.log.service.IAuditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

/**
 * LoggerAuditServiceImpl
 * @description 审计日志实现类-打印日志
 * @author answer_wx
 * @date 2024/1/19 12:05
 * @version 1.0
 */
@Slf4j
@Service
@ConditionalOnProperty(name = "answer.audit-log.log-type", havingValue = "logger", matchIfMissing = true)
public class LoggerAuditServiceImpl implements IAuditService {
    private static final String MSG_PATTERN = "{}|{}|{}|{}|{}|{}|{}|{}";

    /**
     * 格式为：{时间}|{应用名}|{类名}|{方法名}|{用户id}|{用户名}|{租户id}|{操作信息}
     * 例子：2020-02-04 09:13:34.650|user-center|com.answer.user.controller.SysUserController|saveOrUpdate|1|admin|webApp|新增用户:admin
     */
    @Override
    public void save(Audit audit) {
        log.debug(MSG_PATTERN
                , audit.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))
                , audit.getApplicationName(), audit.getClassName(), audit.getMethodName()
                , audit.getUserId(), audit.getUserName(), audit.getClientId()
                , audit.getOperation());
    }
}