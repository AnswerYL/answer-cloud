/**
 * @projectName answer-cloud
 * @package com.answer.generator.service.impl
 * @className com.answer.generator.service.impl.SysGeneratorServiceImpl
 */
package com.answer.generator.service.impl;

import com.answer.common.model.PageResult;
import com.answer.generator.mapper.SysGeneratorMapper;
import com.answer.generator.service.SysGeneratorService;
import com.answer.generator.utils.GenUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * SysGeneratorServiceImpl
 * @description
 * @author answer_wx
 * @date 2024/1/23 19:22
 * @version 1.0
 */
@Service
public class SysGeneratorServiceImpl extends ServiceImpl implements SysGeneratorService {
    @Autowired
    private SysGeneratorMapper sysGeneratorMapper;

    @Override
    public PageResult<Map<String, Object>> queryList(Map<String, Object> map) {
        Page<Map<String, Object>> page = new Page<>(MapUtils.getInteger(map, "page"), MapUtils.getInteger(map, "limit"));

        List<Map<String, Object>> list = sysGeneratorMapper.queryList(page, map);
        return PageResult.<Map<String, Object>>builder().data(list).code(0).count(page.getTotal()).build();
    }

    @Override
    public Map<String, String> queryTable(String tableName) {
        return sysGeneratorMapper.queryTable(tableName);
    }

    @Override
    public List<Map<String, String>> queryColumns(String tableName) {
        return sysGeneratorMapper.queryColumns(tableName);
    }

    @Override
    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (
                ZipOutputStream zip = new ZipOutputStream(outputStream)
        ) {
            for (String tableName : tableNames) {
                //查询表信息
                Map<String, String> table = queryTable(tableName);
                //查询列信息
                List<Map<String, String>> columns = queryColumns(tableName);
                //生成代码
                GenUtils.generatorCode(table, columns, zip);
            }
        } catch (IOException e) {
            log.error("generatorCode-error: ", e);
        }
        return outputStream.toByteArray();
    }
}