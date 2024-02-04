/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.mobile
 * @className com.answer.oauth2.mobile.MobileAuthenticationProvider
 */
package com.answer.oauth2.mobile;

import com.answer.common.oauth2.token.MobileAuthenticationToken;
import com.answer.oauth2.service.impl.UserDetailServiceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * MobileAuthenticationProvider
 * @description
 * @author answer_wx
 * @date 2024/1/24 16:53
 * @version 1.0
 */
@Getter
@Setter
public class MobileAuthenticationProvider implements AuthenticationProvider {

    private UserDetailServiceFactory userDetailsServiceFactory;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) {
        MobileAuthenticationToken authenticationToken = (MobileAuthenticationToken) authentication;
        String mobile = (String) authenticationToken.getPrincipal();
        String password = (String) authenticationToken.getCredentials();
        UserDetails user = userDetailsServiceFactory.getService(authenticationToken).loadUserByMobile(mobile);
        if (user == null) {
            throw new InternalAuthenticationServiceException("手机号或密码错误");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InternalAuthenticationServiceException("手机号或密码错误");
        }
        MobileAuthenticationToken authenticationResult = new MobileAuthenticationToken(user, password, user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }
}