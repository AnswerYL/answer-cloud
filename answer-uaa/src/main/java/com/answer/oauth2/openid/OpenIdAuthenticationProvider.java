/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.openid
 * @className com.answer.oauth2.openid.OpenIdAuthenticationProvider
 */
package com.answer.oauth2.openid;

import com.answer.common.oauth2.token.OpenIdAuthenticationToken;
import com.answer.oauth2.service.impl.UserDetailServiceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * OpenIdAuthenticationProvider
 * @description
 * @author answer_wx
 * @date 2024/1/24 16:55
 * @version 1.0
 */
@Getter
@Setter
public class OpenIdAuthenticationProvider implements AuthenticationProvider {

    private UserDetailServiceFactory userDetailsServiceFactory;

    @Override
    public Authentication authenticate(Authentication authentication) {
        OpenIdAuthenticationToken authenticationToken = (OpenIdAuthenticationToken) authentication;
        String openId = (String) authenticationToken.getPrincipal();
        UserDetails user = userDetailsServiceFactory.getService(authenticationToken).loadUserByUserId(openId);
        if (user == null) {
            throw new InternalAuthenticationServiceException("openId错误");
        }
        OpenIdAuthenticationToken authenticationResult = new OpenIdAuthenticationToken(user, user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OpenIdAuthenticationToken.class.isAssignableFrom(authentication);
    }
}