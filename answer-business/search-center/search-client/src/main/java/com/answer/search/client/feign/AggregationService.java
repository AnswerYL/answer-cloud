/**
 * @projectName answer-cloud
 * @package com.answer.search.client.feign
 * @className com.answer.search.client.feign.AggregationService
 */
package com.answer.search.client.feign;

import com.answer.common.constant.ServiceNameConstants;
import com.answer.search.client.feign.fallback.AggregationServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * AggregationService
 * @description
 * @author answer_wx
 * @date 2024/1/23 20:58
 * @version 1.0
 */
@FeignClient(name = ServiceNameConstants.SEARCH_SERVICE, fallbackFactory = AggregationServiceFallbackFactory.class, decode404 = true)
public interface AggregationService {
    /**
     * 访问统计聚合查询
     * @param indexName 索引名
     * @param routing es的路由
     * @return
     */
    @GetMapping(value = "/agg/requestStat/{indexName}/{routing}" )
    Map<String, Object> requestStatAgg(@PathVariable("indexName" ) String indexName, @PathVariable("routing" ) String routing);
}