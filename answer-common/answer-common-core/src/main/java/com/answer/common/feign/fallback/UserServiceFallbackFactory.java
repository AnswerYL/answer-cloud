/**
 * @projectName answer-cloud
 * @package com.answer.common.feign.fallback
 * @className com.answer.common.feign.fallback.UserServiceFallbackFactory
 */
package com.answer.common.feign.fallback;

import com.answer.common.feign.UserService;
import com.answer.common.model.LoginAppUser;
import com.answer.common.model.SysRole;
import com.answer.common.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.Collections;
import java.util.List;

/**
 * UserServiceFallbackFactory
 * @description userService降级工场
 * @author answer_wx
 * @date 2024/1/18 19:19
 * @version 1.0
 */
@Slf4j
public class UserServiceFallbackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            @Override
            public SysUser selectByUsername(String username) {
                log.error("通过用户名查询用户异常:{}", username, throwable);
                return new SysUser();
            }

            @Override
            public LoginAppUser findByUsername(String username) {
                log.error("通过用户名查询用户异常:{}", username, throwable);
                return new LoginAppUser();
            }

            @Override
            public LoginAppUser findByMobile(String mobile) {
                log.error("通过手机号查询用户异常:{}", mobile, throwable);
                return new LoginAppUser();
            }

            @Override
            public LoginAppUser findByOpenId(String openId) {
                log.error("通过openId查询用户异常:{}", openId, throwable);
                return new LoginAppUser();
            }

            /**
             * 获取带角色的用户信息
             *
             * @param username
             * @return
             */
            @Override
            public SysUser selectRoleUser(String username) {
                log.error("通过用户名查询用户异常:{}", username, throwable);
                return new SysUser();
            }

            /**
             * 获取用户的角色
             *
             * @param id@return
             */
            @Override
            public List<SysRole> findRolesByUserId(Long id) {
                log.error("通过用户id查询角色列表异常:{}", id, throwable);
                return Collections.emptyList();
            }
        };
    }
}