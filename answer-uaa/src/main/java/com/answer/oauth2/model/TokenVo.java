/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.model
 * @className com.answer.oauth2.model.TokenVo
 */
package com.answer.oauth2.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * TokenVo
 * @description
 * @author answer_wx
 * @date 2024/1/24 16:18
 * @version 1.0
 */
@Getter
@Setter
public class TokenVo implements Serializable {
    private static final long serialVersionUID = -6656955957477645319L;
    /**
     * token的值
     */
    private String tokenValue;
    /**
     * 到期时间
     */
    private Date expiration;
    /**
     * 用户名
     */
    private String username;
    /**
     * 所属应用
     */
    private String clientId;
    /**
     * 授权类型
     */
    private String grantType;
    /**
     * 账号类型
     */
    private String accountType;
}