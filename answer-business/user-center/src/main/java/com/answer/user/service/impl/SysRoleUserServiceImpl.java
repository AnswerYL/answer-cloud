/**
 * @projectName answer-cloud
 * @package com.answer.user.service.impl
 * @className com.answer.user.service.impl.SysRoleUserServiceImpl
 */
package com.answer.user.service.impl;

import com.answer.common.model.SysRole;
import com.answer.common.service.impl.SuperServiceImpl;
import com.answer.user.mapper.SysUserRoleMapper;
import com.answer.user.model.SysRoleUser;
import com.answer.user.service.ISysRoleUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * SysRoleUserServiceImpl
 * @description
 * @author answer_wx
 * @date 2024/1/23 21:40
 * @version 1.0
 */
@Slf4j
@Service
public class SysRoleUserServiceImpl extends SuperServiceImpl<SysUserRoleMapper, SysRoleUser> implements ISysRoleUserService {
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public int deleteUserRole(Long userId, Long roleId) {
        return sysUserRoleMapper.deleteUserRole(userId, roleId);
    }

    @Override
    public int saveUserRoles(Long userId, Long roleId) {
        return sysUserRoleMapper.saveUserRoles(userId, roleId);
    }

    @Override
    public List<SysRole> findRolesByUserId(Long userId) {
        return sysUserRoleMapper.findRolesByUserId(userId);
    }

    @Override
    public List<SysRole> findRolesByUserIds(List<Long> userIds) {
        return sysUserRoleMapper.findRolesByUserIds(userIds);
    }
}