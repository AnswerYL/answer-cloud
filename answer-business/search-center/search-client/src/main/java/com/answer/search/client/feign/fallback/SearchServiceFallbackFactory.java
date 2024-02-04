/**
 * @projectName answer-cloud
 * @package com.answer.search.client.feign
 * @className com.answer.search.client.feign.SearchServiceFallbackFactory
 */
package com.answer.search.client.feign.fallback;

import com.answer.common.model.PageResult;
import com.answer.search.client.feign.SearchService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * SearchServiceFallbackFactory
 * @description searchService降级工场
 * @author answer_wx
 * @date 2024/1/23 20:56
 * @version 1.0
 */
@Slf4j
public class SearchServiceFallbackFactory implements FallbackFactory<SearchService> {
    @Override
    public SearchService create(Throwable throwable) {
        return (indexName, searchDto) -> {
            log.error("通过索引{}搜索异常:{}", indexName, throwable);
            return PageResult.<JsonNode>builder().build();
        };
    }
}