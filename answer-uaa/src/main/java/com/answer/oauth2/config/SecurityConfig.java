/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.config
 * @className com.answer.oauth2.config.SecurityConfig
 */
package com.answer.oauth2.config;

import com.answer.common.config.DefaultPasswordConfig;
import com.answer.common.constant.SecurityConstants;
import com.answer.common.oauth2.token.CustomWebAuthenticationDetails;
import com.answer.common.properties.TenantProperties;
import com.answer.oauth2.filter.LoginProcessSetTenantFilter;
import com.answer.oauth2.mobile.MobileAuthenticationSecurityConfig;
import com.answer.oauth2.openid.OpenIdAuthenticationSecurityConfig;
import com.answer.oauth2.password.PasswordAuthenticationProvider;
import com.answer.oauth2.service.impl.UserDetailServiceFactory;
import com.answer.oauth2.tenant.TenantAuthenticationSecurityConfig;
import com.answer.oauth2.tenant.TenantUsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * SecurityConfig
 * @description spring security配置
 * 在WebSecurityConfigurerAdapter不拦截oauth要开放的资源
 * @author answer_wx
 * @date 2024/1/24 16:50
 * @version 1.0
 */
@Configuration
@Import(DefaultPasswordConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired(required = false)
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Resource
    private UserDetailServiceFactory userDetailsServiceFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private LogoutHandler logoutHandler;

    @Resource
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private MobileAuthenticationSecurityConfig mobileAuthenticationSecurityConfig;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TenantAuthenticationSecurityConfig tenantAuthenticationSecurityConfig;

    @Autowired
    private TenantProperties tenantProperties;

    @Autowired
    private AuthenticationDetailsSource<HttpServletRequest, CustomWebAuthenticationDetails> authenticationDetailsSource;

    /**
     * 这一步的配置是必不可少的，否则SpringBoot会自动配置一个AuthenticationManager,覆盖掉内存中的用户
     * @return 认证管理对象
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public TenantUsernamePasswordAuthenticationFilter tenantAuthenticationFilter(AuthenticationManager authenticationManager) {
        TenantUsernamePasswordAuthenticationFilter filter = new TenantUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager);
        filter.setFilterProcessesUrl(SecurityConstants.OAUTH_LOGIN_PRO_URL);
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(SecurityConstants.LOGIN_FAILURE_PAGE));
        filter.setAuthenticationDetailsSource(authenticationDetailsSource);
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                //授权服务器关闭basic认证
                .permitAll()
                .and()
                .logout()
                .logoutUrl(SecurityConstants.LOGOUT_URL)
                .logoutSuccessHandler(logoutSuccessHandler)
                .addLogoutHandler(logoutHandler)
                .clearAuthentication(true)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()
                .apply(mobileAuthenticationSecurityConfig)
                .and()
                .addFilterBefore(new LoginProcessSetTenantFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                // 解决不允许显示在iframe的问题
                .headers().frameOptions().disable().cacheControl();

        if (tenantProperties.getEnable()) {
            //解决不同租户单点登录时角色没变化
            http.formLogin()
                    .loginPage(SecurityConstants.LOGIN_PAGE)
                    .and()
                    .addFilterAt(tenantAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
                    .apply(tenantAuthenticationSecurityConfig);
        } else {
            http.formLogin()
                    .loginPage(SecurityConstants.LOGIN_PAGE)
                    .loginProcessingUrl(SecurityConstants.OAUTH_LOGIN_PRO_URL)
                    .successHandler(authenticationSuccessHandler)
                    .authenticationDetailsSource(authenticationDetailsSource);
        }


        // 基于密码 等模式可以无session,不支持授权码模式
        if (authenticationEntryPoint != null) {
            http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        } else {
            // 授权码模式单独处理，需要session的支持，此模式可以支持所有oauth2的认证
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        }
    }

    /**
     * 全局用户信息
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        PasswordAuthenticationProvider provider = new PasswordAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsServiceFactory(userDetailsServiceFactory);
        auth.authenticationProvider(provider);
    }
	/*public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}*/
}