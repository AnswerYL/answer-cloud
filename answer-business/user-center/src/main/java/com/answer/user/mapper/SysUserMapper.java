/**
 * @projectName answer-cloud
 * @package com.answer.user.mapper
 * @className com.answer.user.mapper.SysUserMapper
 */
package com.answer.user.mapper;

import com.answer.common.db.mapper.SuperMapper;
import com.answer.common.model.SysUser;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * SysUserMapper
 * @description 用户表接口
 * @author answer_wx
 * @date 2024/1/23 21:24
 * @version 1.0
 */
@Mapper
public interface SysUserMapper extends SuperMapper<SysUser> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<SysUser> findList(Page<SysUser> page, @Param("u") Map<String, Object> params);
}