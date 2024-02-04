/**
 * @projectName answer-cloud
 * @package com.answer.search.client.feign
 * @className com.answer.search.client.feign.SearchService
 */
package com.answer.search.client.feign;

import com.answer.common.constant.ServiceNameConstants;
import com.answer.common.model.PageResult;
import com.answer.search.client.feign.fallback.SearchServiceFallbackFactory;
import com.answer.search.model.SearchDto;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * SearchService
 * @description 通用搜索接口--调用search服务端
 * @author answer_wx
 * @date 2024/1/23 20:58
 * @version 1.0
 */
@FeignClient(name = ServiceNameConstants.SEARCH_SERVICE, fallbackFactory = SearchServiceFallbackFactory.class, decode404 = true)
public interface SearchService {
    /**
     * 查询文档列表
     * @param indexName 索引名
     * @param searchDto 搜索Dto
     */
    @PostMapping(value = "/search/{indexName}")
    PageResult<JsonNode> strQuery(@PathVariable("indexName") String indexName, @RequestBody SearchDto searchDto);
}