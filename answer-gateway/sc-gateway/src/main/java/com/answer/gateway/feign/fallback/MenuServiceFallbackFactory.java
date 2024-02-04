/**
 * @projectName answer-cloud
 * @package com.answer.gateway.feign.fallback
 * @className com.answer.gateway.feign.fallback.MenuServiceFallbackFactory
 */
package com.answer.gateway.feign.fallback;

import cn.hutool.core.collection.CollectionUtil;
import com.answer.gateway.feign.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * MenuServiceFallbackFactory
 * @description MenuService接口降级工厂
 * @author answer_wx
 * @date 2024/1/21 18:51
 * @version 1.0
 */
@Slf4j
@Component
public class MenuServiceFallbackFactory implements FallbackFactory<MenuService> {
    @Override
    public MenuService create(Throwable cause) {
        return roleIds -> {
            log.error("调用findByRoleCodes异常：{}", roleIds, cause);
            return CollectionUtil.newArrayList();
        };
    }
}