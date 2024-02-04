/**
 * @projectName answer-cloud
 * @package com.answer.common.lb.annotation
 * @className com.answer.common.lb.annotation.EnableFeignInterceptor
 */
package com.answer.common.lb.annotation;

import com.answer.common.lb.config.FeignHttpInterceptorConfig;
import com.answer.common.lb.config.FeignInterceptorConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * EnableFeignInterceptor
 * @description 开启feign拦截器传递数据给下游服务，包含基础数据和http的相关数据
 * @author answer_wx
 * @date 2024/1/19 19:49
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({FeignInterceptorConfig.class, FeignHttpInterceptorConfig.class})
public @interface EnableFeignInterceptor {
}