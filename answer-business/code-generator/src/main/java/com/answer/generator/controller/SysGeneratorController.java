/**
 * @projectName answer-cloud
 * @package com.answer.generator.controller
 * @className com.answer.generator.controller.SysGeneratorController
 */
package com.answer.generator.controller;

import com.answer.common.model.PageResult;
import com.answer.generator.service.SysGeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * SysGeneratorController
 * @description
 * @author answer_wx
 * @date 2024/1/23 19:19
 * @version 1.0
 */
@RestController
@Api(tags = "代码生成器api")
@RequestMapping("/generator")
public class SysGeneratorController {

    @Autowired
    private SysGeneratorService sysGeneratorService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表")
    @ResponseBody
    @GetMapping("/list")
    public PageResult getTableList(@RequestParam Map<String, Object> params) {
        return sysGeneratorService.queryList(params);
    }

    /**
     * 生成代码FileUtil
     */
    @ApiOperation(value = "生成代码")
    @GetMapping("/code")
    public void makeCode(String tables, HttpServletResponse response) throws IOException {
        byte[] data = sysGeneratorService.generatorCode(tables.split(","));
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"generator.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}