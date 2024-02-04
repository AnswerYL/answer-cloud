/**
 * @projectName answer-cloud
 * @package com.answer.file.service
 * @className com.answer.file.service.IFileService
 */
package com.answer.file.service;

import com.answer.common.model.PageResult;
import com.answer.file.model.FileInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.Map;

/**
 * IFileService
 * @description 文件service接口
 * @author answer_wx
 * @date 2024/1/23 20:02
 * @version 1.0
 */
public interface IFileService extends IService<FileInfo> {
    FileInfo upload(MultipartFile file ) throws Exception;

    PageResult<FileInfo> findList(Map<String, Object> params);

    void delete(String id);

    void out(String id, OutputStream os);
}