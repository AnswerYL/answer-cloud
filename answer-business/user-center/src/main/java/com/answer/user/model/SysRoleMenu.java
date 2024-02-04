/**
 * @projectName answer-cloud
 * @package com.answer.user.model
 * @className com.answer.user.model.SysRoleMenu
 */
package com.answer.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

/**
 * SysRoleMenu
 * @description
 * @author answer_wx
 * @date 2024/1/23 21:19
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role_menu")
public class SysRoleMenu extends Model<SysRoleMenu> {
    private Long roleId;
    private Long menuId;
}