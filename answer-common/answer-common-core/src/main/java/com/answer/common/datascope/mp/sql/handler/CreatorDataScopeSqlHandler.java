/**
 * @projectName answer-cloud
 * @package com.answer.common.datascope.mp.sql.handler
 * @className com.answer.common.datascope.mp.sql.handler.CreatorDataScopeSqlHandler
 */
package com.answer.common.datascope.mp.sql.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.answer.common.context.LoginUserContextHolder;
import com.answer.common.enums.DataScope;
import com.answer.common.feign.UserService;
import com.answer.common.model.SysRole;
import com.answer.common.model.SysUser;
import com.answer.common.properties.DataScopeProperties;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

/**
 * CreatorDataScopeSqlHandler
 * @description 个人权限处理器
 * @author answer_wx
 * @date 2024/1/18 22:30
 * @version 1.0
 */
public class CreatorDataScopeSqlHandler implements SqlHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private DataScopeProperties dataScopeProperties;

    /**
     * 返回需要增加的where条件，返回空字符的话则代表不需要权限控制
     *
     * @return where条件
     * 如果角色是全部权限的话则不进行控制，如果是个人权限的话则自动加入create_id = user_id
     */

    @Override
    public String handleScopeSql() {
        SysUser user = LoginUserContextHolder.getUser();
        Assert.notNull(user, "登陆人不能为空");
        List<SysRole> roleList = userService.findRolesByUserId(user.getId());
        return StrUtil.isBlank(dataScopeProperties.getCreatorIdColumnName())
                || CollUtil.isEmpty(roleList)
                || roleList.stream().anyMatch(item -> Objects.isNull(item.getDataScope()) || DataScope.ALL.equals(item.getDataScope()))
                ? DO_NOTHING :
                // 这里确保有配置权限范围控制的字段
                // 1. 如果没有配置角色的情况默认采用只读全部的记录
                // 2. 如果有配置角色的话判断是否存在有ALL获取null的情况，如果没有ALL的话读取个人创建记录
                String.format("%s = '%s'", dataScopeProperties.getCreatorIdColumnName(), user.getId());
    }
}