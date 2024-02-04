/**
 * @projectName answer-cloud
 * @package com.answer.user.mapper
 * @className com.answer.user.mapper.SysRoleMenuMapper
 */
package com.answer.user.mapper;

import com.answer.common.db.mapper.SuperMapper;
import com.answer.common.model.SysMenu;
import com.answer.user.model.SysRoleMenu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * SysRoleMenuMapper
 * @description 角色菜单
 * @author answer_wx
 * @date 2024/1/23 21:25
 * @version 1.0
 */
@Mapper
public interface SysRoleMenuMapper extends SuperMapper<SysRoleMenu> {
    @Insert("insert into sys_role_menu(role_id, menu_id) values(#{roleId}, #{menuId})" )
    int save(@Param("roleId" ) Long roleId, @Param("menuId" ) Long menuId);

    int delete(@Param("roleId" ) Long roleId, @Param("menuId" ) Long menuId);

    List<SysMenu> findMenusByRoleIds(@Param("roleIds" ) Set<Long> roleIds, @Param("type" ) Integer type);

    List<SysMenu> findMenusByRoleCodes(@Param("roleCodes" ) Set<String> roleCodes, @Param("type" ) Integer type);
}