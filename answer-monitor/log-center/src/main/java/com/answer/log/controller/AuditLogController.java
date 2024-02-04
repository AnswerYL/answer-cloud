/**
 * @projectName answer-cloud
 * @package com.answer.log.controller
 * @className com.answer.log.controller.AuditLogController
 */
package com.answer.log.controller;

import com.answer.common.model.PageResult;
import com.answer.search.client.service.IQueryService;
import com.answer.search.model.SearchDto;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuditLogController
 * @description 审计日志
 * @author answer_wx
 * @date 2024/1/24 16:04
 * @version 1.0
 */
@RestController
@Api(tags = "审计日志api")
public class AuditLogController {

    @Autowired
    private IQueryService queryService;


    @ApiOperation(value = "审计日志全文搜索列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "queryStr", value = "搜索关键字", dataType = "String")
    })
    @GetMapping(value = "/auditLog")
    public PageResult<JsonNode> getPage(SearchDto searchDto) {
        searchDto.setIsHighlighter(true);
        searchDto.setSortCol("timestamp");
        return queryService.strQuery("audit-log-*", searchDto);
    }
}