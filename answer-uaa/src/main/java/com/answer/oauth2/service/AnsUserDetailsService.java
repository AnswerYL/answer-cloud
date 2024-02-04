/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.service
 * @className com.answer.oauth2.service.AnsUserDetailsService
 */
package com.answer.oauth2.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;

/**
 * AnsUserDetailsService
 * @description
 * @author answer_wx
 * @date 2024/1/24 16:31
 * @version 1.0
 */
public interface AnsUserDetailsService extends UserDetailsService {
    /**
     * 判断实现类是否属于该类型
     * @param accountType 账号类型
     */
    boolean supports(String accountType);

    /**
     * 根据电话号码查询用户
     *
     * @param mobile
     * @return
     */
    UserDetails loadUserByMobile(String mobile);

    /**
     * 根据用户id/openId查询用户
     * @param userId 用户id/openId
     */
    SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException;
}