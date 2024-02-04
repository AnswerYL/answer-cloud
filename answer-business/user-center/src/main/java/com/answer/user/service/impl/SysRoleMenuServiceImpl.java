/**
 * @projectName answer-cloud
 * @package com.answer.user.service.impl
 * @className com.answer.user.service.impl.SysRoleMenuServiceImpl
 */
package com.answer.user.service.impl;

import com.answer.common.model.SysMenu;
import com.answer.common.service.impl.SuperServiceImpl;
import com.answer.user.mapper.SysRoleMenuMapper;
import com.answer.user.model.SysRoleMenu;
import com.answer.user.service.ISysRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * SysRoleMenuServiceImpl
 * @description
 * @author answer_wx
 * @date 2024/1/23 21:47
 * @version 1.0
 */
@Slf4j
@Service
public class SysRoleMenuServiceImpl extends SuperServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {
    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public int save(Long roleId, Long menuId) {
        return sysRoleMenuMapper.save(roleId, menuId);
    }

    @Override
    public int delete(Long roleId, Long menuId) {
        return sysRoleMenuMapper.delete(roleId, menuId);
    }

    @Override
    public List<SysMenu> findMenusByRoleIds(Set<Long> roleIds, Integer type) {
        return sysRoleMenuMapper.findMenusByRoleIds(roleIds, type);
    }

    @Override
    public List<SysMenu> findMenusByRoleCodes(Set<String> roleCodes, Integer type) {
        return sysRoleMenuMapper.findMenusByRoleCodes(roleCodes, type);
    }
}