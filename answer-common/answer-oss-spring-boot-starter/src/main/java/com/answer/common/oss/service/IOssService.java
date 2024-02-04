/**
 * @projectName answer-cloud
 * @package com.answer.common.oss.service
 * @className com.answer.common.oss.service.IOssService
 */
package com.answer.common.oss.service;

import com.answer.common.oss.model.ObjectInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * IOssService
 * @description oss接口
 * @author answer_wx
 * @date 2024/1/21 16:55
 * @version 1.0
 */
public interface IOssService {
    /**
     * 上传对象
     * @param objectName 对象名
     * @param is 对象流
     */
    ObjectInfo upload(String objectName, InputStream is);

    /**
     * 上传对象
     * @param file 对象
     */
    ObjectInfo upload(MultipartFile file);

    /**
     * 删除对象
     * @param objectKey 对象标识
     */
    void delete(String objectKey);

    /**
     * 查看文件
     * @param objectPath 对象路径
     * @param os 输出流
     */
    void view(String objectPath, OutputStream os);
}