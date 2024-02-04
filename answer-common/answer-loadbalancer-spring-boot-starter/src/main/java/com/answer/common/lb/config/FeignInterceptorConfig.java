/**
 * @projectName answer-cloud
 * @package com.answer.common.lb.config
 * @className com.answer.common.lb.config.FeignInterceptorConfig
 */
package com.answer.common.lb.config;

import cn.hutool.core.util.StrUtil;
import com.answer.common.constant.SecurityConstants;
import com.answer.common.context.TenantContextHolder;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * FeignInterceptorConfig
 * @description feign拦截器，只包含基础数据
 * @author answer_wx
 * @date 2024/1/19 19:30
 * @version 1.0
 */
public class FeignInterceptorConfig {
    @Bean
    public RequestInterceptor baseFeignInterceptor() {
        return template -> {
            //传递client
            String tenant = TenantContextHolder.getTenant();
            if (StrUtil.isNotEmpty(tenant)) {
                template.header(SecurityConstants.TENANT_HEADER, tenant);
            }
        };
    }
}