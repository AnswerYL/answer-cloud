/**
 * @projectName answer-cloud
 * @package com.answer.search.service
 * @className com.answer.search.service.IAggregationService
 */
package com.answer.search.service;

import java.io.IOException;
import java.util.Map;

/**
 * IAggregationService
 * @description
 * @author answer_wx
 * @date 2024/1/23 20:45
 * @version 1.0
 */
public interface IAggregationService {
    /**
     * 访问统计聚合查询
     * @param indexName 索引名
     * @param routing es的路由
     * @return
     */
    Map<String, Object> requestStatAgg(String indexName, String routing) throws IOException;
}