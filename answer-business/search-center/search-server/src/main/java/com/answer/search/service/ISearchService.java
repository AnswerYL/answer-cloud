/**
 * @projectName answer-cloud
 * @package com.answer.search.service
 * @className com.answer.search.service.ISearchService
 */
package com.answer.search.service;

import com.answer.common.model.PageResult;
import com.answer.search.model.SearchDto;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * ISearchService
 * @description
 * @author answer_wx
 * @date 2024/1/23 20:42
 * @version 1.0
 */
public interface ISearchService {
    /**
     * StringQuery通用搜索
     * @param indexName 索引名
     * @param searchDto 搜索Dto
     * @return
     */
    PageResult<JsonNode> strQuery(String indexName, SearchDto searchDto) throws IOException;
}