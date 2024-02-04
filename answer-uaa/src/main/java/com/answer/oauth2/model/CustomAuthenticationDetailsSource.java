/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.model
 * @className com.answer.oauth2.model.CustomAuthenticationDetailsSource
 */
package com.answer.oauth2.model;

import com.answer.common.constant.SecurityConstants;
import com.answer.common.oauth2.token.CustomWebAuthenticationDetails;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * CustomAuthenticationDetailsSource
 * @description 表单登录的认证信息对象
 * @author answer_wx
 * @date 2024/1/24 16:17
 * @version 1.0
 */
@Component
public class CustomAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, CustomWebAuthenticationDetails> {

    @Override
    public CustomWebAuthenticationDetails buildDetails(HttpServletRequest context) {
        String remoteAddress = context.getRemoteAddr();
        HttpSession session = context.getSession(false);
        String sessionId = session != null ? session.getId() : null;
        String accountType = context.getParameter(SecurityConstants.ACCOUNT_TYPE_PARAM_NAME);
        return new CustomWebAuthenticationDetails(remoteAddress, sessionId, accountType);
    }
}