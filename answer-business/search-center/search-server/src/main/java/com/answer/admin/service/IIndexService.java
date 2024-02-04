/**
 * @projectName answer-cloud
 * @package com.answer.admin.service
 * @className com.answer.admin.service.IIndexService
 */
package com.answer.admin.service;

import com.answer.admin.model.IndexDto;
import com.answer.common.model.PageResult;

import java.io.IOException;
import java.util.Map;

/**
 * IIndexService
 * @description 索引接口
 * @author answer_wx
 * @date 2024/1/23 20:27
 * @version 1.0
 */
public interface IIndexService {
    /**
     * 创建索引
     */
    boolean create(IndexDto indexDto) throws IOException;

    /**
     * 删除索引
     * @param indexName 索引名
     */
    boolean delete(String indexName) throws IOException;

    /**
     * 索引列表
     * @param queryStr 搜索字符串
     * @param indices 默认显示的索引名
     */
    PageResult<Map<String, String>> list(String queryStr, String indices) throws IOException;

    /**
     * 显示索引明细
     * @param indexName 索引名
     */
    Map<String, Object> show(String indexName) throws IOException;
}