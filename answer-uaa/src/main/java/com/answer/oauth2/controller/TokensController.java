/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.controller
 * @className com.answer.oauth2.controller.TokensController
 */
package com.answer.oauth2.controller;

import com.answer.common.constant.SecurityConstants;
import com.answer.common.model.PageResult;
import com.answer.common.model.Result;
import com.answer.common.oauth2.util.AuthUtils;
import com.answer.oauth2.model.TokenVo;
import com.answer.oauth2.service.ITokensService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TokensController
 * @description token管理接口
 * @author answer_wx
 * @date 2024/1/24 17:20
 * @version 1.0
 */
@Api(tags = "Token管理api")
@Slf4j
@RestController
@RequestMapping("/tokens")
public class TokensController {

    @Resource
    private ITokensService tokensService;

    @Resource
    private ClientDetailsService clientDetailsService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    @ApiOperation(value = "token列表")
    public PageResult<TokenVo> list(@RequestParam Map<String, Object> params, String tenantId) {
        return tokensService.listTokens(params, tenantId);
    }

    @GetMapping("/key")
    @ApiOperation(value = "获取jwt密钥")
    public Result<String> key(HttpServletRequest request) {
        try {
            String[] clientArr = AuthUtils.extractClient(request);
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientArr[0]);
            if (clientDetails == null || !passwordEncoder.matches(clientArr[1], clientDetails.getClientSecret())) {
                throw new BadCredentialsException("应用ID或密码错误");
            }
        } catch (AuthenticationException ae) {
            return Result.failed(ae.getMessage());
        }
        ClassPathResource res = new ClassPathResource(SecurityConstants.RSA_PUBLIC_KEY);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(res.getInputStream()))) {
            return Result.succeed(br.lines().collect(Collectors.joining("\n")));
        } catch (IOException ioe) {
            log.error("key error", ioe);
            return Result.failed(ioe.getMessage());
        }
    }
}