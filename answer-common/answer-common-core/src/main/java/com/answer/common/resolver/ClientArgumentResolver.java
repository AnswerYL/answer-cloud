/**
 * @projectName answer-cloud
 * @package com.answer.common.resolver
 * @className com.answer.common.resolver.ClientArgumentResolver
 */
package com.answer.common.resolver;

import cn.hutool.core.util.StrUtil;
import com.answer.common.annotation.LoginClient;
import com.answer.common.constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * ClientArgumentResolver
 * @description head中的应用参数注入clientId中
 * @author answer_wx
 * @date 2024/1/18 19:44
 * @version 1.0
 */

@Slf4j
public class ClientArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginClient.class) && parameter.getParameterType().equals(String.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String clientId = request.getHeader(SecurityConstants.TENANT_HEADER);
        if (StrUtil.isBlank(clientId)) {
            log.warn("resolveArgument error clientId is empty");
        }
        return clientId;
    }
}