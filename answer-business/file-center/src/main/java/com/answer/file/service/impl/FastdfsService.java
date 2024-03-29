/**
 * @projectName answer-cloud
 * @package com.answer.file.service.impl
 * @className com.answer.file.service.impl.FastdfsService
 */
package com.answer.file.service.impl;

import com.answer.common.oss.model.ObjectInfo;
import com.answer.common.oss.properties.FileServerProperties;
import com.answer.common.oss.template.FdfsTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.OutputStream;

/**
 * FastdfsService
 * @description
 * @author answer_wx
 * @date 2024/1/23 20:08
 * @version 1.0
 */
@Service
@ConditionalOnProperty(prefix = FileServerProperties.PREFIX, name = "type", havingValue = FileServerProperties.TYPE_FDFS)
public class FastdfsService extends AbstractIFileService{
    @Resource
    private FdfsTemplate fdfsTemplate;

    @Override
    protected String fileType() {
        return FileServerProperties.TYPE_FDFS;
    }

    @Override
    protected ObjectInfo uploadFile(MultipartFile file) {
        return fdfsTemplate.upload(file);
    }

    @Override
    protected void deleteFile(String objectPath) {
        fdfsTemplate.delete(objectPath);
    }

    @Override
    public void out(String id, OutputStream os) {
    }
}