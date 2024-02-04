/**
 * @projectName answer-cloud
 * @package com.answer.user.mapper
 * @className com.answer.user.mapper.SysRoleMapper
 */
package com.answer.user.mapper;

import com.answer.common.db.mapper.SuperMapper;
import com.answer.common.model.SysRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * SysRoleMapper
 * @description 角色
 * @author answer_wx
 * @date 2024/1/23 21:27
 * @version 1.0
 */
@Mapper
public interface SysRoleMapper extends SuperMapper<SysRole> {
    List<SysRole> findList(Page<SysRole> page, @Param("r") Map<String, Object> params);

    List<SysRole> findAll();
}