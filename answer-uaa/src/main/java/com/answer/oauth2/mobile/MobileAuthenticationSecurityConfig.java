/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.mobile
 * @className com.answer.oauth2.mobile.MobileAuthenticationSecurityConfig
 */
package com.answer.oauth2.mobile;

import com.answer.oauth2.service.impl.UserDetailServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * MobileAuthenticationSecurityConfig
 * @description mobile的相关处理配置
 * @author answer_wx
 * @date 2024/1/24 16:52
 * @version 1.0
 */
@Component
public class MobileAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Resource
    private UserDetailServiceFactory userDetailsServiceFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(HttpSecurity http) {
        //mobile provider
        MobileAuthenticationProvider provider = new MobileAuthenticationProvider();
        provider.setUserDetailsServiceFactory(userDetailsServiceFactory);
        provider.setPasswordEncoder(passwordEncoder);
        http.authenticationProvider(provider);
    }
}