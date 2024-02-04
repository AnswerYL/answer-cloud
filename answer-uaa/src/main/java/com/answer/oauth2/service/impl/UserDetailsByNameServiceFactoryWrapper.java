/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.service.impl
 * @className com.answer.oauth2.service.impl.UserDetailsByNameServiceFactoryWrapper
 */
package com.answer.oauth2.service.impl;

import com.answer.oauth2.service.AnsUserDetailsService;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.Assert;

/**
 * UserDetailsByNameServiceFactoryWrapper
 * @description 重写 UserDetailsByNameServiceWrapper 支持多帐户类型
 * @author answer_wx
 * @date 2024/1/24 16:42
 * @version 1.0
 */
public class UserDetailsByNameServiceFactoryWrapper <T extends Authentication> implements
        AuthenticationUserDetailsService<T>, InitializingBean {

    @Setter
    private UserDetailServiceFactory userDetailServiceFactory;

    public UserDetailsByNameServiceFactoryWrapper() {

    }

    public UserDetailsByNameServiceFactoryWrapper(final UserDetailServiceFactory userDetailServiceFactory) {
        Assert.notNull(userDetailServiceFactory, "userDetailServiceFactory cannot be null.");
        this.userDetailServiceFactory = userDetailServiceFactory;
    }

    /**
     * Check whether all required properties have been set.
     *
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() {
        Assert.notNull(this.userDetailServiceFactory, "UserDetailServiceFactory must be set");
    }

    /**
     * Get the UserDetails object from the wrapped UserDetailsService implementation
     */
    @Override
    public UserDetails loadUserDetails(T authentication) throws UsernameNotFoundException {
        AnsUserDetailsService userDetailsService;
        if (authentication instanceof PreAuthenticatedAuthenticationToken) {
            userDetailsService = this.userDetailServiceFactory.getService((Authentication)authentication.getPrincipal());
        } else {
            userDetailsService = this.userDetailServiceFactory.getService(authentication);
        }
        return userDetailsService.loadUserByUsername(authentication.getName());
    }
}