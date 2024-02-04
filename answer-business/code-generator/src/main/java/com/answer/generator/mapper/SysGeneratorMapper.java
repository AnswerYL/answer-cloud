/**
 * @projectName answer-cloud
 * @package com.answer.generator.mapper
 * @className com.answer.generator.mapper.SysGeneratorMapper
 */
package com.answer.generator.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * SysGeneratorMapper
 * @description
 * @author answer_wx
 * @date 2024/1/23 19:19
 * @version 1.0
 */
@Mapper
@Component
public interface SysGeneratorMapper {
    List<Map<String, Object>> queryList(Page<Map<String, Object>> page, @Param("p") Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);
}