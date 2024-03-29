/**
 * @projectName answer-cloud
 * @package com.answer.common.feign
 * @className com.answer.common.feign.UserService
 */
package com.answer.common.feign;

import com.answer.common.constant.ServiceNameConstants;
import com.answer.common.feign.fallback.UserServiceFallbackFactory;
import com.answer.common.model.LoginAppUser;
import com.answer.common.model.SysRole;
import com.answer.common.model.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * UserService
 * @description
 * @author answer_wx
 * @date 2024/1/18 19:17
 * @version 1.0
 */
@FeignClient(name = ServiceNameConstants.USER_SERVICE, fallbackFactory = UserServiceFallbackFactory.class, decode404 = true)
public interface UserService {

    /**
     * feign rpc访问远程/users/{username}接口
     * 查询用户实体对象SysUser
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/users/name/{username}")
    SysUser selectByUsername(@PathVariable("username") String username);

    /**
     * feign rpc访问远程/users-anon/login接口
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/users-anon/login", params = "username")
    LoginAppUser findByUsername(@RequestParam("username") String username);

    /**
     * 通过手机号查询用户、角色信息
     *
     * @param mobile 手机号
     */
    @GetMapping(value = "/users-anon/mobile", params = "mobile")
    LoginAppUser findByMobile(@RequestParam("mobile") String mobile);

    /**
     * 根据OpenId查询用户信息
     *
     * @param openId openId
     */
    @GetMapping(value = "/users-anon/openId", params = "openId")
    LoginAppUser findByOpenId(@RequestParam("openId") String openId);


    /**
     * 获取带角色的用户信息
     * @param username
     * @return
     */
    @GetMapping(value = "/users/roleUser/{username}")
    public SysUser selectRoleUser(@PathVariable("username") String username);

    /**
     * 获取用户的角色
     *
     * @param
     * @return
     */
    @GetMapping("/users/{id}/roles")
    public List<SysRole> findRolesByUserId(@PathVariable("id") Long id);
}