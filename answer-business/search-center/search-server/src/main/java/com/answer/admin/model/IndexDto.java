/**
 * @projectName answer-cloud
 * @package com.answer.admin.model
 * @className com.answer.admin.model.IndexDto
 */
package com.answer.admin.model;

import lombok.Data;

/**
 * IndexDto
 * @description
 * @author answer_wx
 * @date 2024/1/23 20:26
 * @version 1.0
 */
@Data
public class IndexDto {
    /**
     * 索引名
     */
    private String indexName;
    /**
     * 分片数 number_of_shards
     */
    private Integer numberOfShards;
    /**
     * 副本数 number_of_replicas
     */
    private Integer numberOfReplicas;
    /**
     * 类型
     */
    private String type;
    /**
     * mappings内容
     */
    private String mappingsSource;
}