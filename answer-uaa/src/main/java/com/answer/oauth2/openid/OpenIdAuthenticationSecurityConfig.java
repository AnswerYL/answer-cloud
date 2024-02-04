/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.openid
 * @className com.answer.oauth2.openid.OpenIdAuthenticationSecurityConfig
 */
package com.answer.oauth2.openid;

import com.answer.oauth2.service.impl.UserDetailServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;

/**
 * OpenIdAuthenticationSecurityConfig
 * @description openId的相关处理配置
 * @author answer_wx
 * @date 2024/1/24 16:55
 * @version 1.0
 */
@Component
public class OpenIdAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private UserDetailServiceFactory userDetailsServiceFactory;

    @Override
    public void configure(HttpSecurity http) {
        //openId provider
        OpenIdAuthenticationProvider provider = new OpenIdAuthenticationProvider();
        provider.setUserDetailsServiceFactory(userDetailsServiceFactory);
        http.authenticationProvider(provider);
    }
}