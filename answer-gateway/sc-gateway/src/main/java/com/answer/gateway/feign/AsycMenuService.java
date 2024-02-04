/**
 * @projectName answer-cloud
 * @package com.answer.gateway.feign
 * @className com.answer.gateway.feign.AsynMenuService
 */
package com.answer.gateway.feign;

import com.answer.common.model.SysMenu;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;

/**
 * AsynMenuService
 * @description 异步Menu服务
 * @author answer_wx
 * @date 2024/1/21 18:54
 * @version 1.0
 */
@Component
public class AsycMenuService {

    @Lazy
    @Resource
    private MenuService menuService;

    @Async
    public Future<List<SysMenu>> findByRoleCodes(String roleCodes) {
        List<SysMenu> result = menuService.findByRoleCodes(roleCodes);
        return new AsyncResult<>(result);
    }

}