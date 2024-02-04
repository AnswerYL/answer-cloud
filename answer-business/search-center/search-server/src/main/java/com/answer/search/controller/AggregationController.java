/**
 * @projectName answer-cloud
 * @package com.answer.search.controller
 * @className com.answer.search.controller.AggregationController
 */
package com.answer.search.controller;

import com.answer.search.service.IAggregationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * AggregationController
 * @description 聚合搜索
 * @author answer_wx
 * @date 2024/1/23 20:52
 * @version 1.0
 */
@Slf4j
@RestController
@Api(tags = "聚合搜索模块api")
@RequestMapping("/agg")
public class AggregationController {
    private final IAggregationService aggregationService;

    public AggregationController(IAggregationService aggregationService) {
        this.aggregationService = aggregationService;
    }

    /**
     * 访问统计聚合查询
     * @param indexName 索引名
     * @param routing es的路由
     */
    @ApiOperation(value = "访问统计聚合查询")
    @GetMapping("/requestStat/{indexName}/{routing}")
    public Map<String, Object> requestStatAgg(@PathVariable String indexName, @PathVariable String routing) throws IOException {
        return aggregationService.requestStatAgg(indexName, routing);
    }
}