/**
 * @projectName answer-cloud
 * @package com.answer.common.oauth2.properties
 * @className com.answer.common.oauth2.properties.ValidateCodeProperties
 */
package com.answer.common.oauth2.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * ValidateCodeProperties
 * @description 验证码配置
 * @author answer_wx
 * @date 2024/1/19 17:59
 * @version 1.0
 */
@Getter
@Setter
public class ValidateCodeProperties {
    /**
     * 设置认证通时不需要验证码的clientId
     */
    private String[] ignoreClientCode = {};
}