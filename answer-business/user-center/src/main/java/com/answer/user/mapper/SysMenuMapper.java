/**
 * @projectName answer-cloud
 * @package com.answer.user.mapper
 * @className com.answer.user.mapper.SysMenuMapper
 */
package com.answer.user.mapper;

import com.answer.common.db.mapper.SuperMapper;
import com.answer.common.model.SysMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * SysMenuMapper
 * @description 菜单
 * @author answer_wx
 * @date 2024/1/23 21:27
 * @version 1.0
 */
@Mapper
public interface SysMenuMapper extends SuperMapper<SysMenu> {
}