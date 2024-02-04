/**
 * @projectName answer-cloud
 * @package com.answer.user.service
 * @className com.answer.user.service.ISysRoleUserService
 */
package com.answer.user.service;

import com.answer.common.model.SysRole;
import com.answer.common.service.ISuperService;
import com.answer.user.model.SysRoleUser;

import java.util.List;

/**
 * ISysRoleUserService
 * @description
 * @author answer_wx
 * @date 2024/1/23 21:30
 * @version 1.0
 */
public interface ISysRoleUserService extends ISuperService<SysRoleUser> {

    int deleteUserRole(Long userId, Long roleId);

    int saveUserRoles(Long userId, Long roleId);

    /**
     * 根据用户id获取角色
     *
     * @param userId
     * @return
     */
    List<SysRole> findRolesByUserId(Long userId);

    /**
     * 根据用户ids 获取
     *
     * @param userIds
     * @return
     */
    List<SysRole> findRolesByUserIds(List<Long> userIds);
}