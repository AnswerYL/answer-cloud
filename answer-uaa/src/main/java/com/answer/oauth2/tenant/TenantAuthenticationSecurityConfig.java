/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.tenant
 * @className com.answer.oauth2.tenant.TenantAuthenticationSecurityConfig
 */
package com.answer.oauth2.tenant;

import com.answer.oauth2.service.impl.UserDetailServiceFactory;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;

/**
 * TenantAuthenticationSecurityConfig
 * @description
 * @author answer_wx
 * @date 2024/1/24 16:59
 * @version 1.0
 */
@Component
public class TenantAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final UserDetailServiceFactory userDetailsServiceFactory;

    private final PasswordEncoder passwordEncoder;

    public TenantAuthenticationSecurityConfig(UserDetailServiceFactory userDetailsServiceFactory, PasswordEncoder passwordEncoder) {
        this.userDetailsServiceFactory = userDetailsServiceFactory;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(HttpSecurity http) {
        TenantAuthenticationProvider provider = new TenantAuthenticationProvider();
        provider.setUserDetailsServiceFactory(userDetailsServiceFactory);
        provider.setPasswordEncoder(passwordEncoder);
        http.authenticationProvider(provider);
    }
}