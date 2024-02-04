/**
 * @projectName answer-cloud
 * @package com.answer.search.client.service
 * @className com.answer.search.client.service.IQueryService
 */
package com.answer.search.client.service;

import com.answer.common.model.PageResult;
import com.answer.search.model.LogicDelDto;
import com.answer.search.model.SearchDto;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

/**
 * IQueryService
 * @description 搜索客户端接口
 * @author answer_wx
 * @date 2024/1/23 21:08
 * @version 1.0
 */
public interface IQueryService {
    /**
     * 查询文档列表
     * @param indexName 索引名
     * @param searchDto 搜索Dto
     */
    PageResult<JsonNode> strQuery(String indexName, SearchDto searchDto);

    /**
     * 查询文档列表
     * @param indexName 索引名
     * @param searchDto 搜索Dto
     * @param logicDelDto 逻辑删除Dto
     */
    PageResult<JsonNode> strQuery(String indexName, SearchDto searchDto, LogicDelDto logicDelDto);

    /**
     * 访问统计聚合查询
     * @param indexName 索引名
     * @param routing es的路由
     */
    Map<String, Object> requestStatAgg(String indexName, String routing);
}