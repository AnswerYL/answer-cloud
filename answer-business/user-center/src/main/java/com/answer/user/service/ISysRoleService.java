/**
 * @projectName answer-cloud
 * @package com.answer.user.service
 * @className com.answer.user.service.ISysRoleService
 */
package com.answer.user.service;

import com.answer.common.model.PageResult;
import com.answer.common.model.Result;
import com.answer.common.model.SysRole;
import com.answer.common.service.ISuperService;

import java.util.List;
import java.util.Map;

/**
 * ISysRoleService
 * @description
 * @author answer_wx
 * @date 2024/1/23 21:30
 * @version 1.0
 */
public interface ISysRoleService extends ISuperService<SysRole> {
    void saveRole(SysRole sysRole) throws Exception;

    void deleteRole(Long id);

    /**
     * 角色列表
     * @param params
     * @return
     */
    PageResult<SysRole> findRoles(Map<String, Object> params);

    /**
     * 新增或更新角色
     * @param sysRole
     * @return Result
     */
    Result saveOrUpdateRole(SysRole sysRole) throws Exception;

    /**
     * 查询所有角色
     * @return
     */
    List<SysRole> findAll();
}