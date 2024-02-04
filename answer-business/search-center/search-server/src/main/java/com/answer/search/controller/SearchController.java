/**
 * @projectName answer-cloud
 * @package com.answer.search.controller
 * @className com.answer.search.controller.SearchController
 */
package com.answer.search.controller;

import com.answer.common.model.PageResult;
import com.answer.search.model.SearchDto;
import com.answer.search.service.ISearchService;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * SearchController
 * @description 通用搜索
 * @author answer_wx
 * @date 2024/1/23 20:49
 * @version 1.0
 */
@Slf4j
@RestController
@Api(tags = "通用搜索模块api")
@RequestMapping("/search")
public class SearchController {
    private final ISearchService searchService;

    public SearchController(ISearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * 查询文档列表
     * @param indexName 索引名
     * @param searchDto 搜索Dto
     */
    @ApiOperation(value = "查询文档列表")
    @PostMapping("/{indexName}")
    public PageResult<JsonNode> strQuery(@PathVariable String indexName, @RequestBody(required = false) SearchDto searchDto) throws IOException {
        if (searchDto == null) {
            searchDto = new SearchDto();
        }
        return searchService.strQuery(indexName, searchDto);
    }
}