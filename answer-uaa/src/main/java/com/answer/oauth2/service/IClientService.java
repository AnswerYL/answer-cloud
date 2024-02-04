/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.service
 * @className com.answer.oauth2.service.IClientService
 */
package com.answer.oauth2.service;

import com.answer.common.model.PageResult;
import com.answer.common.model.Result;
import com.answer.common.service.ISuperService;
import com.answer.oauth2.model.Client;

import java.util.Map;

/**
 * IClientService
 * @description
 * @author answer_wx
 * @date 2024/1/24 16:24
 * @version 1.0
 */
public interface IClientService extends ISuperService<Client> {
    Result saveClient(Client clientDto) throws Exception;

    /**
     * 查询应用列表
     * @param params
     * @param isPage 是否分页
     */
    PageResult<Client> listClient(Map<String, Object> params, boolean isPage);

    void delClient(long id);

    Client loadClientByClientId(String clientId);
}