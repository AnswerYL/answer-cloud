/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.tenant
 * @className com.answer.oauth2.tenant.TenantAuthenticationProvider
 */
package com.answer.oauth2.tenant;

import com.answer.common.oauth2.token.TenantUsernamePasswordAuthenticationToken;
import com.answer.oauth2.password.PasswordAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * TenantAuthenticationProvider
 * @description 增加租户id，解决不同租户单点登录时角色没变化
 * @author answer_wx
 * @date 2024/1/24 16:57
 * @version 1.0
 */
public class TenantAuthenticationProvider extends PasswordAuthenticationProvider {
    @Override
    protected Authentication createSuccessAuthentication(Object principal,
                                                         Authentication authentication, UserDetails user) {
        TenantUsernamePasswordAuthenticationToken authenticationToken = (TenantUsernamePasswordAuthenticationToken) authentication;
        TenantUsernamePasswordAuthenticationToken result = new TenantUsernamePasswordAuthenticationToken(
                principal, authentication.getCredentials(), user.getAuthorities(), authenticationToken.getClientId());
        result.setDetails(authenticationToken.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TenantUsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}