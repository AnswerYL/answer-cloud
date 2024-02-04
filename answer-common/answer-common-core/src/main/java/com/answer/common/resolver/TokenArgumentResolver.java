/**
 * @projectName answer-cloud
 * @package com.answer.common.resolver
 * @className com.answer.common.resolver.TokenArgumentResolver
 */
package com.answer.common.resolver;

import com.answer.common.annotation.LoginUser;
import com.answer.common.feign.UserService;
import com.answer.common.model.SysUser;
import com.answer.common.utils.LoginUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * TokenArgumentResolver
 * @description Token转化SysUser
 * @author answer_wx
 * @date 2024/1/18 19:33
 * @version 1.0
 */
@Slf4j
public class TokenArgumentResolver implements HandlerMethodArgumentResolver {

    private UserService userService;

    public TokenArgumentResolver(UserService userService) {
        this.userService = userService;
    }

    /**
     * 入参筛选
     * @param parameter 参数集合
     * @return 格式化后的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class) && parameter.getParameterType().equals(SysUser.class);

    }

    /**
     *
     * @param parameter 入参集合
     * @param mavContainer model 和 view
     * @param webRequest web相关
     * @param binderFactory 入参解析
     * @return 包装对象
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        LoginUser loginUser = parameter.getParameterAnnotation(LoginUser.class);
        boolean isFull = false;
        if (loginUser != null) {
            isFull = loginUser.isFull();
        }
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        //账号类型
        // String accountType = request.getHeader(SecurityConstants.ACCOUNT_TYPE_HEADER);

        return LoginUserUtil.getCurrentUser(request, isFull);
    }
}