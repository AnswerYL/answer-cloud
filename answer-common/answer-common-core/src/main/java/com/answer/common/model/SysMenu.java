/**
 * @projectName answer-cloud
 * @package com.answer.common.model
 * @className com.answer.common.model.SysMenu
 */
package com.answer.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

/**
 * SysMenu
 * @description
 * @author answer_wx
 * @date 2024/1/18 23:01
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menu")
public class SysMenu extends SuperEntity {
    private static final long serialVersionUID = 749360940290141180L;

    private Long parentId;
    private String name;
    private String css;
    private String url;
    private String path;
    private Integer sort;
    private Integer type;
    private Boolean hidden;
    /**
     * 请求的类型
     */
    private String pathMethod;
    private Long creatorId;

    @TableField(exist = false)
    private List<SysMenu> subMenus;
    @TableField(exist = false)
    private Long roleId;
    @TableField(exist = false)
    private Set<Long> menuIds;
}