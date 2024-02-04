/**
 * @projectName answer-cloud
 * @package com.answer.admin.controller
 * @className com.answer.admin.controller.IndexController
 */
package com.answer.admin.controller;

import com.answer.admin.model.IndexDto;
import com.answer.admin.properties.IndexProperties;
import com.answer.admin.service.IIndexService;
import com.answer.common.model.PageResult;
import com.answer.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * IndexController
 * @description 索引操作
 * @author answer_wx
 * @date 2024/1/23 20:31
 * @version 1.0
 */
@Slf4j
@RestController
@Api(tags = "索引管理api" )
@RequestMapping("/admin" )
public class IndexController {

    @Autowired
    private IIndexService indexService;

    @Autowired
    private IndexProperties indexProperties;

    @ApiOperation(value = "创建索引" )
    @PostMapping("/index" )
    public Result createIndex(@RequestBody IndexDto indexDto) throws IOException {
        if (indexDto.getNumberOfShards() == null) {
            indexDto.setNumberOfShards(1);
        }
        if (indexDto.getNumberOfReplicas() == null) {
            indexDto.setNumberOfReplicas(0);
        }
        indexService.create(indexDto);
        return Result.succeed("操作成功" );
    }

    /**
     * 索引列表
     */
    @ApiOperation(value = "索引列表" )
    @GetMapping("/indices" )
    public PageResult<Map<String, String>> list(@RequestParam(required = false) String queryStr) throws IOException {
        return indexService.list(queryStr, indexProperties.getShow());
    }

    /**
     * 索引明细
     */
    @ApiOperation(value = "索引明细" )
    @GetMapping("/index" )
    public Result<Map<String, Object>> showIndex(String indexName) throws IOException {
        Map<String, Object> result = indexService.show(indexName);
        return Result.succeed(result);
    }

    /**
     * 删除索引
     */
    @ApiOperation(value = "删除索引" )
    @DeleteMapping("/index" )
    public Result deleteIndex(String indexName) throws IOException {
        indexService.delete(indexName);
        return Result.succeed("操作成功" );
    }
}