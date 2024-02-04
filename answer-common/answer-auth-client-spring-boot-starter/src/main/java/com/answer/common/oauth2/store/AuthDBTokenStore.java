/**
 * @projectName answer-cloud
 * @package com.answer.common.oauth2.store
 * @className com.answer.common.oauth2.store.AuthDbTokenStore
 */
package com.answer.common.oauth2.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * AuthDbTokenStore
 * @description 认证服务器使用数据库存取令牌
 * @author answer_wx
 * @date 2024/1/19 19:12
 * @version 1.0
 */
@Configuration
@ConditionalOnProperty(prefix = "answer.oauth2.token.store", name = "type", havingValue = "db")
public class AuthDBTokenStore {
    @Autowired
    private DataSource dataSource;

    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }
}