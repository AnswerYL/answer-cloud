/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.service.impl
 * @className com.answer.oauth2.service.impl.UserDetailServiceImpl
 */
package com.answer.oauth2.service.impl;

import com.answer.common.constant.SecurityConstants;
import com.answer.common.feign.UserService;
import com.answer.common.model.LoginAppUser;
import com.answer.oauth2.service.AnsUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UserDetailServiceImpl
 * @description
 * @author answer_wx
 * @date 2024/1/24 16:45
 * @version 1.0
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements AnsUserDetailsService {

    private static final String ACCOUNT_TYPE = SecurityConstants.DEF_ACCOUNT_TYPE;

    @Resource
    private UserService userService;

    @Override
    public boolean supports(String accountType) {
        return ACCOUNT_TYPE.equals(accountType);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        LoginAppUser loginAppUser = userService.findByUsername(username);
        if (loginAppUser == null) {
            throw new InternalAuthenticationServiceException("用户名或密码错误");
        }
        return checkUser(loginAppUser);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String openId) {
        LoginAppUser loginAppUser = userService.findByOpenId(openId);
        return checkUser(loginAppUser);
    }

    @Override
    public UserDetails loadUserByMobile(String mobile) {
        LoginAppUser loginAppUser = userService.findByMobile(mobile);
        return checkUser(loginAppUser);
    }

    private LoginAppUser checkUser(LoginAppUser loginAppUser) {
        if (loginAppUser != null && !loginAppUser.isEnabled()) {
            throw new DisabledException("用户已作废");
        }
        return loginAppUser;
    }
}