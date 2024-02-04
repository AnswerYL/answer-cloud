/**
 * @projectName answer-cloud
 * @package com.answer.common.properties
 * @className com.answer.common.properties.TenantProperties
 */
package com.answer.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.ArrayList;
import java.util.List;

/**
 * TenantProperties
 * @description 多租户配置
 * @author answer_wx
 * @date 2024/1/18 23:04
 * @version 1.0
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "answer.tenant")
@RefreshScope
public class TenantProperties {
    /**
     * 是否开启多租户
     */
    private Boolean enable = false;

    /**
     * 配置不进行多租户隔离的表名
     */
    private List<String> ignoreTables = new ArrayList<>();

    /**
     * 配置不进行多租户隔离的sql
     * 需要配置mapper的全路径如：com.answer.user.mapper.SysUserMapper.findList
     */
    private List<String> ignoreSqls = new ArrayList<>();
}