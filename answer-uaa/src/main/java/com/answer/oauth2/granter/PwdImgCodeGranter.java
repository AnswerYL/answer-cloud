/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.granter
 * @className com.answer.oauth2.granter.PwdImgCodeGranter
 */
package com.answer.oauth2.granter;

import com.answer.oauth2.service.IValidateCodeService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * PwdImgCodeGranter
 * @description 密码加图像验证码授权模式
 * @author answer_wx
 * @date 2024/1/24 17:14
 * @version 1.0
 */
public class PwdImgCodeGranter extends ResourceOwnerPasswordTokenGranter {

    private static final String GRANT_TYPE = "password_code";

    private final IValidateCodeService validateCodeService;

    public PwdImgCodeGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices
            , ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, IValidateCodeService validateCodeService) {
        super(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.validateCodeService = validateCodeService;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        String deviceId = parameters.get("deviceId");
        String validCode = parameters.get("validCode");
        //校验图形验证码
        validateCodeService.validate(deviceId, validCode);

        return super.getOAuth2Authentication(client, tokenRequest);
    }
}