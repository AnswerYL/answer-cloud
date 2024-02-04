/**
 * @projectName answer-cloud
 * @package com.answer.search.client.feign.fallback
 * @className com.answer.search.client.feign.fallback.AggregationServiceFallbackFactory
 */
package com.answer.search.client.feign.fallback;

import cn.hutool.core.map.MapUtil;
import com.answer.common.constant.ServiceNameConstants;
import com.answer.search.client.feign.AggregationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * AggregationServiceFallbackFactory
 * @description AggregationService降级工厂
 * @author answer_wx
 * @date 2024/1/23 21:05
 * @version 1.0
 */
@Slf4j
public class AggregationServiceFallbackFactory implements FallbackFactory<AggregationService> {
    @Override
    public AggregationService create(Throwable throwable) {
        return (indexName, routing) -> {
            log.error("通过索引{}搜索异常:{}", indexName, throwable);
            return MapUtil.newHashMap(0);
        };
    }
}