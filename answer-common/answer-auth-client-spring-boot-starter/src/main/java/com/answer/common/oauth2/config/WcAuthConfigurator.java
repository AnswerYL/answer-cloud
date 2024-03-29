/**
 * @projectName answer-cloud
 * @package com.answer.common.oauth2.config
 * @className com.answer.common.oauth2.config.WcAuthConfigurator
 */
package com.answer.common.oauth2.config;

import com.answer.common.oauth2.util.AuthUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.websocket.server.ServerEndpointConfig;

/**
 * WcAuthConfigurator
 * @description webSocket鉴权配置
 * @author answer_wx
 * @date 2024/1/19 18:57
 * @version 1.0
 */
@Slf4j
public class WcAuthConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public boolean checkOrigin(String originHeaderValue) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        try {
            //检查token有效性
            AuthUtils.checkAccessToken(servletRequestAttributes.getRequest());
        } catch (Exception e) {
            log.error("WebSocket-auth-error", e);
            return false;
        }
        return super.checkOrigin(originHeaderValue);
    }
}