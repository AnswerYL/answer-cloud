/**
 * @projectName answer-cloud
 * @package com.answer.user.service
 * @className com.answer.user.service.ISysRoleMenuService
 */
package com.answer.user.service;

import com.answer.common.model.SysMenu;
import com.answer.common.service.ISuperService;
import com.answer.user.model.SysRoleMenu;

import java.util.List;
import java.util.Set;

/**
 * ISysRoleMenuService
 * @description
 * @author answer_wx
 * @date 2024/1/23 21:31
 * @version 1.0
 */
public interface ISysRoleMenuService extends ISuperService<SysRoleMenu> {
    int save(Long roleId, Long menuId);

    int delete(Long roleId, Long menuId);

    List<SysMenu> findMenusByRoleIds(Set<Long> roleIds, Integer type);

    List<SysMenu> findMenusByRoleCodes(Set<String> roleCodes, Integer type);
}