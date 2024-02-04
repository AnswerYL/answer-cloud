/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.service.impl
 * @className com.answer.oauth2.service.impl.UserDetailServiceFactory
 */
package com.answer.oauth2.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.answer.common.constant.SecurityConstants;
import com.answer.common.oauth2.util.AuthUtils;
import com.answer.oauth2.service.AnsUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserDetailServiceFactory
 * @description 用户service工厂
 * @author answer_wx
 * @date 2024/1/24 16:43
 * @version 1.0
 */
@Slf4j
@Service
public class UserDetailServiceFactory {

    private static final String ERROR_MSG = "找不到账号类型为 {} 的实现类";

    @Resource
    private List<AnsUserDetailsService> ansUserDetailsServices;

    public AnsUserDetailsService getService(Authentication authentication) {
        String accountType = AuthUtils.getAccountType(authentication);
        return this.getService(accountType);
    }

    public AnsUserDetailsService getService(String accountType) {
        if (StrUtil.isEmpty(accountType)) {
            accountType = SecurityConstants.DEF_ACCOUNT_TYPE;
        }
        log.info("UserDetailServiceFactory.getService:{}", accountType);
        if (CollUtil.isNotEmpty(ansUserDetailsServices)) {
            for (AnsUserDetailsService userService : ansUserDetailsServices) {
                if (userService.supports(accountType)) {
                    return userService;
                }
            }
        }
        throw new InternalAuthenticationServiceException(StrUtil.format(ERROR_MSG, accountType));
    }
}