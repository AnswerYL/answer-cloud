/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.handler
 * @className com.answer.oauth2.handler.OauthLogoutSuccessHandler
 */
package com.answer.oauth2.handler;

import cn.hutool.core.util.StrUtil;
import com.answer.common.model.Result;
import com.answer.common.oauth2.properties.SecurityProperties;
import com.answer.common.utils.JsonUtil;
import com.answer.oauth2.service.impl.UnifiedLogoutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * OauthLogoutSuccessHandler
 * @description
 * @author answer_wx
 * @date 2024/1/24 17:06
 * @version 1.0
 */
@Slf4j
public class OauthLogoutSuccessHandler implements LogoutSuccessHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Resource
    private UnifiedLogoutService unifiedLogoutService;

    @Resource
    private SecurityProperties securityProperties;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        if (securityProperties.getAuth().getUnifiedLogout()) {
            unifiedLogoutService.allLogout();
        }

        String redirectUri = request.getParameter("redirect_uri");
        if (StrUtil.isNotEmpty(redirectUri)) {
            //重定向指定的地址
            redirectStrategy.sendRedirect(request, response, redirectUri);
        } else {
            response.setStatus(HttpStatus.OK.value());
            response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            PrintWriter writer = response.getWriter();
            String jsonStr = JsonUtil.toJSONString(Result.succeed("登出成功"));
            writer.write(jsonStr);
            writer.flush();
        }
    }
}