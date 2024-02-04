/**
 * @projectName answer-cloud
 * @package com.answer.generator.service
 * @className com.answer.generator.service.SysGeneratorService
 */
package com.answer.generator.service;

import com.answer.common.model.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * SysGeneratorService
 * @description
 * @author answer_wx
 * @date 2024/1/23 19:21
 * @version 1.0
 */
@Service
public interface SysGeneratorService extends IService{
    PageResult queryList(Map<String, Object> map);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);

    byte[] generatorCode(String[] tableNames);
}