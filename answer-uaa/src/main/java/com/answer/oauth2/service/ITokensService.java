/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.service
 * @className com.answer.oauth2.service.ITokensService
 */
package com.answer.oauth2.service;

import com.answer.common.model.PageResult;
import com.answer.oauth2.model.TokenVo;

import java.util.Map;

/**
 * ITokensService
 * @description
 * @author answer_wx
 * @date 2024/1/24 16:25
 * @version 1.0
 */
public interface ITokensService {
    /**
     * 查询token列表
     * @param params 请求参数
     * @param clientId 应用id
     */
    PageResult<TokenVo> listTokens(Map<String, Object> params, String clientId);
}