/**
 * @projectName answer-cloud
 * @package com.answer.common.oauth2.constants
 * @className com.answer.common.oauth2.constants.AuthoritiesEnum
 */
package com.answer.common.oauth2.constants;

import lombok.Getter;

/**
 * AuthoritiesEnum
 * @description 权限常量
 * @author answer_wx
 * @date 2024/1/19 19:04
 * @version 1.0
 */
public enum AuthoritiesEnum {
    /**
     * 管理员
     */
    ADMIN("ROLE_ADMIN"),
    /**
     * 普通用户
     */
    USER("ROLE_USER"),
    /**
     * 匿名用户
     */
    ANONYMOUS("ROLE_ANONYMOUS");

    private String role;

    AuthoritiesEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}