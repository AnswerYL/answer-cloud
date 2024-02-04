/**
 * @projectName answer-cloud
 * @package com.answer.file.mapper
 * @className com.answer.file.mapper.FileMapper
 */
package com.answer.file.mapper;

import com.answer.common.db.mapper.SuperMapper;
import com.answer.file.model.FileInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * FileMapper
 * @description
 * @author answer_wx
 * @date 2024/1/23 20:05
 * @version 1.0
 */
@Mapper
public interface FileMapper extends SuperMapper<FileInfo> {
    List<FileInfo> findList(Page<FileInfo> page, @Param("f") Map<String, Object> params);
}