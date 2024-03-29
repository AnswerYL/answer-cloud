/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.service.impl
 * @className com.answer.oauth2.service.impl.ClientServiceImpl
 */
package com.answer.oauth2.service.impl;

import com.answer.common.constant.SecurityConstants;
import com.answer.common.context.LoginUserContextHolder;
import com.answer.common.lock.DistributedLock;
import com.answer.common.model.PageResult;
import com.answer.common.model.Result;
import com.answer.common.redis.template.CustomRedisTemplate;
import com.answer.common.service.impl.SuperServiceImpl;
import com.answer.oauth2.mapper.ClientMapper;
import com.answer.oauth2.model.Client;
import com.answer.oauth2.service.IClientService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ClientServiceImpl
 * @description
 * @author answer_wx
 * @date 2024/1/24 16:32
 * @version 1.0
 */
@Slf4j
@Service
public class ClientServiceImpl extends SuperServiceImpl<ClientMapper, Client> implements IClientService {

    private final static String LOCK_KEY_CLIENTID = "clientId:";

    @Autowired
    private CustomRedisTemplate customRedisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DistributedLock lock;

    @Override
    public Result saveClient(Client client) throws Exception {
        client.setClientSecret(passwordEncoder.encode(client.getClientSecretStr()));
        String clientId = client.getClientId();
        if (client.getId() == null) {
            client.setCreatorId(LoginUserContextHolder.getUser().getId());
        }
        super.saveOrUpdateIdempotency(client, lock
                , LOCK_KEY_CLIENTID+clientId
                , new QueryWrapper<Client>().eq("client_id", clientId)
                , clientId + "已存在");
        return Result.succeed("操作成功");
    }

    @Override
    public PageResult<Client> listClient(Map<String, Object> params, boolean isPage) {
        Page<Client> page;
        if (isPage) {
            page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        } else {
            page = new Page<>(1, -1);
        }
        List<Client> list = baseMapper.findList(page, params);
        page.setRecords(list);
        return PageResult.<Client>builder().data(list).code(0).count(page.getTotal()).build();
    }

    @Override
    public void delClient(long id) {
        String clientId = baseMapper.selectById(id).getClientId();
        baseMapper.deleteById(id);
        customRedisTemplate.del(clientRedisKey(clientId));
    }

    @Override
    public Client loadClientByClientId(String clientId) {
        QueryWrapper<Client> wrapper = Wrappers.query();
        wrapper.eq("client_id", clientId);
        return this.getOne(wrapper);
    }

    private String clientRedisKey(String clientId) {
        return SecurityConstants.CACHE_CLIENT_KEY + ":" + clientId;
    }
}