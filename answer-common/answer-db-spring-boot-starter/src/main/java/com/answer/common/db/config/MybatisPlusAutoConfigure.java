/**
 * @projectName answer-cloud
 * @package com.answer.db.config
 * @className com.answer.db.config.MybatisPlusAutoConfigure
 */
package com.answer.common.db.config;

import com.answer.common.datascope.mp.interceptor.DataScopeInnerInterceptor;
import com.answer.common.datascope.mp.interceptor.EnableQuerySqlLogInnerInterceptor;
import com.answer.common.datascope.mp.sql.handler.CreatorDataScopeSqlHandler;
import com.answer.common.datascope.mp.sql.handler.SqlHandler;
import com.answer.common.properties.DataScopeProperties;
import com.answer.common.properties.TenantProperties;
import com.answer.common.db.interceptor.CustomTenantInterceptor;
import com.answer.common.db.properties.MybatisPlusAutoFillProperties;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * MybatisPlusAutoConfigure
 * @description Mybatis自动配置
 * @author answer_wx
 * @date 2024/1/19 10:53
 * @version 1.0
 */
@EnableConfigurationProperties({MybatisPlusAutoFillProperties.class, DataScopeProperties.class})
public class MybatisPlusAutoConfigure {
    @Autowired
    private TenantLineHandler tenantLineHandler;

    @Autowired
    private TenantProperties tenantProperties;

    @Autowired
    private MybatisPlusAutoFillProperties autoFillProperties;

    @Autowired
    private DataScopeProperties dataScopeProperties;

    @Bean
    @ConditionalOnMissingBean
    public SqlHandler sqlHandler() {
        return new CreatorDataScopeSqlHandler();
    }

    /**
     * 分页插件，自动识别数据库类型
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor(SqlHandler sqlHandler) {
        MybatisPlusInterceptor mpInterceptor = new MybatisPlusInterceptor();
        boolean enableTenant = tenantProperties.getEnable();
        //是否开启多租户隔离
        if (enableTenant) {
            CustomTenantInterceptor tenantInterceptor = new CustomTenantInterceptor(
                    tenantLineHandler, tenantProperties.getIgnoreSqls());
            mpInterceptor.addInnerInterceptor(tenantInterceptor);
        }
        if (dataScopeProperties.getEnabled()) {
            DataScopeInnerInterceptor dataScopeInnerInterceptor = new DataScopeInnerInterceptor(dataScopeProperties, sqlHandler);
            mpInterceptor.addInnerInterceptor(Boolean.TRUE.equals(dataScopeProperties.getEnabledSqlDebug())
                    ? new EnableQuerySqlLogInnerInterceptor(dataScopeInnerInterceptor) : dataScopeInnerInterceptor);
        }
        mpInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return mpInterceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "answer.mybatis-plus.auto-fill", name = "enabled", havingValue = "true", matchIfMissing = true)
    public MetaObjectHandler metaObjectHandler() {
        return new DateMetaObjectHandler(autoFillProperties);
    }
}