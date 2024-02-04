/**
 * @projectName answer-cloud
 * @package com.answer.common.zk
 * @className com.answer.common.zk.ZookeeperAutoConfiguration
 */
package com.answer.common.zk;

import com.answer.common.zk.properties.ZookeeperProperty;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * ZookeeperAutoConfiguration
 * @description ZK自动配置类
 * @author answer_wx
 * @date 2024/1/21 17:17
 * @version 1.0
 */
@EnableConfigurationProperties(ZookeeperProperty.class)
@ComponentScan
public class ZookeeperAutoConfiguration {
    /**
     * 初始化连接
     */
    @Bean(initMethod = "start", destroyMethod = "close")
    @ConditionalOnMissingBean
    public CuratorFramework curatorFramework(ZookeeperProperty property) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(property.getBaseSleepTime(), property.getMaxRetries());
        return CuratorFrameworkFactory.builder()
                .connectString(property.getConnectString())
                .connectionTimeoutMs(property.getConnectionTimeout())
                .sessionTimeoutMs(property.getSessionTimeout())
                .retryPolicy(retryPolicy)
                .build();
    }
}