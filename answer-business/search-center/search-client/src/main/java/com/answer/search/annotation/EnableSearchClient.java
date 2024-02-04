/**
 * @projectName answer-cloud
 * @package com.answer.search.annotation
 * @className com.answer.search.annotation.EnableSearchClient
 */
package com.answer.search.annotation;

import com.answer.search.client.feign.fallback.AggregationServiceFallbackFactory;
import com.answer.search.client.feign.fallback.SearchServiceFallbackFactory;
import com.answer.search.client.service.impl.QueryServiceImpl;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * EnableSearchClient
 * @description 启用search客户端注解
 * 控制是否加载搜索中心客户端的Service
 * @author answer_wx
 * @date 2024/1/23 20:55
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableFeignClients(basePackages = "com.answer")
@Import({SearchServiceFallbackFactory.class, AggregationServiceFallbackFactory.class, QueryServiceImpl.class})
public @interface EnableSearchClient {
}