/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.mapper
 * @className com.answer.oauth2.mapper.ClientMapper
 */
package com.answer.oauth2.mapper;

import com.answer.common.db.mapper.SuperMapper;
import com.answer.oauth2.model.Client;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * ClientMapper
 * @description
 * @author answer_wx
 * @date 2024/1/24 16:33
 * @version 1.0
 */
@Mapper
public interface ClientMapper extends SuperMapper<Client> {
    List<Client> findList(Page<Client> page, @Param("params") Map<String, Object> params );
}