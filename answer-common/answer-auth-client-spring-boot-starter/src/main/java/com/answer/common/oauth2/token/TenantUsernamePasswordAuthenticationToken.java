/**
 * @projectName answer-cloud
 * @package com.answer.common.oauth2.token
 * @className com.answer.common.oauth2.token.TenantUsernamePasswordAuthenticationToken
 */
package com.answer.common.oauth2.token;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * TenantUsernamePasswordAuthenticationToken
 * @description 增加租户id，解决不同租户单点登录时角色没变化
 * @author answer_wx
 * @date 2024/1/19 19:20
 * @version 1.0
 */
public class TenantUsernamePasswordAuthenticationToken  extends UsernamePasswordAuthenticationToken {
    private static final long serialVersionUID = -5638287853803374687L;

    /**
     * 租户id
     */
    @Getter
    private final String clientId;

    public TenantUsernamePasswordAuthenticationToken(Object principal, Object credentials, String clientId) {
        super(principal, credentials);
        this.clientId = clientId;
    }

    public TenantUsernamePasswordAuthenticationToken(Object principal, Object credentials,
                                                     Collection<? extends GrantedAuthority> authorities, String clientId) {
        super(principal, credentials, authorities);
        this.clientId = clientId;
    }
}