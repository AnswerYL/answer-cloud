/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.handler.decryptParamHandler
 * @className com.answer.oauth2.handler.decryptParamHandler.RsaDecryptParamHandler
 */
package com.answer.oauth2.handler.decryptParamHandler;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.answer.common.oauth2.properties.AuthProperties;
import com.answer.common.oauth2.properties.SecurityProperties;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * RsaDecryptParamHandler
 * @description 使用rsa算法对参数进行解密
 * @author answer_wx
 * @date 2024/1/24 17:08
 * @version 1.0
 */
@Service
public class RsaDecryptParamHandler implements IDecryptParamHandler  {

    private final AuthProperties authProperties;

    public RsaDecryptParamHandler(SecurityProperties securityProperties) {
        this.authProperties = securityProperties.getAuth();
    }

    @Override
    public void decrypt(String[] paramNames, Map<String, String> parameters) {
        RSA rsaTools = SecureUtil.rsa(authProperties.getDecryptParamPrivateKey(), null);
        String value;
        for (String param : paramNames) {
            value = parameters.get(param);
            if (StrUtil.isNotEmpty(value)) {
                //解密
                value = this.decrypt(rsaTools, value, param);
                //更新参数为解密后的值
                parameters.put(param, value);
            }
        }
    }

    private String decrypt(RSA rsaTools, String value, String param) {
        try {
            return rsaTools.decryptStr(value, KeyType.PrivateKey);
        } catch (Exception e) {
            throw new OAuth2Exception("参数 " + param + " 解密失败！");
        }
    }
}