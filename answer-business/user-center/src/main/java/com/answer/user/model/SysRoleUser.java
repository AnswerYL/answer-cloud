/**
 * @projectName answer-cloud
 * @package com.answer.user.model
 * @className com.answer.user.model.SysRoleUser
 */
package com.answer.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

/**
 * SysRoleUser
 * @description
 * @author answer_wx
 * @date 2024/1/23 21:18
 * @version 1.0
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role_user")
public class SysRoleUser extends Model<SysRoleUser> {
    private Long userId;
    private Long roleId;
}