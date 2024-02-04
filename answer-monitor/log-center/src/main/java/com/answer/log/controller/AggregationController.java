/**
 * @projectName answer-cloud
 * @package com.answer.log.controller
 * @className com.answer.log.controller.AggregationController
 */
package com.answer.log.controller;

import com.answer.search.client.service.IQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * AggregationController
 * @description 访问统计
 * @author answer_wx
 * @date 2024/1/24 16:03
 * @version 1.0
 */
@RestController
@Api(tags = "日志管理api")
public class AggregationController {
    @Autowired
    private IQueryService queryService;

    @ApiOperation(value = "访问统计")
    @GetMapping(value = "/requestStat")
    public Map<String, Object> requestStatAgg() {
        return queryService.requestStatAgg("point-log-*","request-statistics");
    }
}