/**
 * @projectName answer-cloud
 * @package com.answer.gateway.feign
 * @className com.answer.gateway.feign.MenuService
 */
package com.answer.gateway.feign;

import com.answer.common.constant.ServiceNameConstants;
import com.answer.common.model.SysMenu;
import com.answer.gateway.feign.fallback.MenuServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * MenuService
 * @description menu接口
 * @author answer_wx
 * @date 2024/1/21 18:50
 * @version 1.0
 */
@FeignClient(name = ServiceNameConstants.USER_SERVICE, fallbackFactory = MenuServiceFallbackFactory.class, decode404 = true)
public interface MenuService {
    /**
     * 角色菜单列表
     * @param roleCodes
     */
    @GetMapping(value = "/menus/{roleCodes}")
    List<SysMenu> findByRoleCodes(@PathVariable("roleCodes") String roleCodes);
}