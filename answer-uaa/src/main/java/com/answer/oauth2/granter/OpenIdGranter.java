/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.granter
 * @className com.answer.oauth2.granter.OpenIdGranter
 */
package com.answer.oauth2.granter;

import com.answer.common.oauth2.token.OpenIdAuthenticationToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * OpenIdGranter
 * @description openid授权模式
 * @author answer_wx
 * @date 2024/1/24 17:13
 * @version 1.0
 */
public class OpenIdGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "openId";

    private final AuthenticationManager authenticationManager;

    public OpenIdGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices
            , ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        String openId = parameters.get("openId");

        Authentication userAuth = new OpenIdAuthenticationToken(openId);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        userAuth = authenticationManager.authenticate(userAuth);
        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate openId: " + openId);
        }

        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }
}