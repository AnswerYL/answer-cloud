/**
 * @projectName answer-cloud
 * @package com.answer.search.service.impl
 * @className com.answer.search.service.impl.SearchServiceImpl
 */
package com.answer.search.service.impl;

import com.answer.common.es.utils.SearchBuilder;
import com.answer.common.model.PageResult;
import com.answer.search.model.SearchDto;
import com.answer.search.service.ISearchService;
import com.fasterxml.jackson.databind.JsonNode;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * SearchServiceImpl
 * @description 通用搜索
 * @author answer_wx
 * @date 2024/1/23 20:46
 * @version 1.0
 */
@Service
public class SearchServiceImpl implements ISearchService {

    private final RestHighLevelClient client;

    public SearchServiceImpl(RestHighLevelClient client) {
        this.client = client;
    }

    /**
     * StringQuery通用搜索
     * @param indexName 索引名
     * @param searchDto 搜索Dto
     * @return
     */
    @Override
    public PageResult<JsonNode> strQuery(String indexName, SearchDto searchDto) throws IOException {
        return SearchBuilder.builder(client, indexName)
                .setStringQuery(searchDto.getQueryStr())
                .addSort(searchDto.getSortCol(), searchDto.getSortOrder())
                .setIsHighlight(searchDto.getIsHighlighter())
                .getPage(searchDto.getPage(), searchDto.getLimit());
    }
}