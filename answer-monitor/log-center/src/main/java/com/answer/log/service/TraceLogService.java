/**
 * @projectName answer-cloud
 * @package com.answer.log.service
 * @className com.answer.log.service.TraceLogService
 */
package com.answer.log.service;

import cn.hutool.core.util.StrUtil;
import com.answer.common.utils.JsonUtil;
import com.answer.log.model.TraceLog;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TraceLogService
 * @description 日志链路service
 * @author answer_wx
 * @date 2024/1/24 16:01
 * @version 1.0
 */
@Service
public class TraceLogService {
    public List<TraceLog> transTraceLog(List<JsonNode> jsonNodeList) {
        List<TraceLog> logList = new ArrayList<>();
        Set<String> logSet = new HashSet<>();
        jsonNodeList.forEach(e -> {
            TraceLog log = JsonUtil.toObject(e, TraceLog.class);
            String spanId = log.getSpanId();
            if (StrUtil.isNotEmpty(spanId)) {
                if (spanId.length() == 1) {
                    log.setParentId("-1");
                } else {
                    log.setParentId(spanId.substring(0, spanId.length() - 2));
                }
                if (checkLog(genLogKey(log), logSet)) {
                    logList.add(log);
                }
            }
        });
        return logList;
    }

    /**
     * 通过集合来去重
     */
    private boolean checkLog(String logKey, Set<String> logSet) {
        if (logSet.contains(logKey)) {
            return false;
        } else {
            logSet.add(logKey);
            return true;
        }
    }

    private String genLogKey(TraceLog log) {
        return StrUtil.format("{}_{}_{}_{}", log.getAppName(), log.getSpanId(), log.getServerIp(), log.getServerPort());
    }
}