/**
 * @projectName answer-cloud
 * @package com.answer.file.controller
 * @className com.answer.file.controller.FileController
 */
package com.answer.file.controller;

import com.answer.common.model.PageResult;
import com.answer.common.model.Result;
import com.answer.file.model.FileInfo;
import com.answer.file.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * FileController
 * @description 文件操作
 * @author answer_wx
 * @date 2024/1/23 20:14
 * @version 1.0
 */
@RestController
@Api(tags = "文件管理api")
public class FileController {
    @Resource
    private IFileService fileService;

    /**
     * 文件上传
     * 根据fileType选择上传方式
     *
     * @param file
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("/files-anon" )
    public FileInfo upload(@RequestParam("file" ) MultipartFile file) throws Exception {
        return fileService.upload(file);
    }

    /**
     * 文件删除
     *
     * @param id
     */
    @ApiOperation(value = "文件删除")
    @DeleteMapping("/files/{id}" )
    public Result delete(@PathVariable String id) {
        try {
            fileService.delete(id);
            return Result.succeed("操作成功" );
        } catch (Exception ex) {
            return Result.failed("操作失败" );
        }
    }

    /**
     * 文件查询
     *
     * @param params
     * @return
     */
    @ApiOperation(value = "文件查询")
    @GetMapping("/files" )
    public PageResult<FileInfo> findFiles(@RequestParam Map<String, Object> params) {
        return fileService.findList(params);
    }
}