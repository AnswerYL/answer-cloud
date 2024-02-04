/**
 * @projectName answer-cloud
 * @package com.answer.common.lb.config
 * @className com.answer.common.lb.config.VersionRegisterBeanPostProcessor
 */
package com.answer.common.lb.config;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.answer.common.constant.CommonConstant;
import com.answer.common.constant.ConfigConstants;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * VersionRegisterBeanPostProcessor
 * @description 将版本注册到注册中心的组件
 * @author answer_wx
 * @date 2024/1/19 19:38
 * @version 1.0
 */
public class VersionRegisterBeanPostProcessor implements BeanPostProcessor {
    @Value("${"+ ConfigConstants.CONFIG_LOADBALANCE_VERSION+":}")
    private String version;
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof NacosDiscoveryProperties && StringUtils.isNotBlank(version)){
            NacosDiscoveryProperties nacosDiscoveryProperties = (NacosDiscoveryProperties) bean;
            nacosDiscoveryProperties.getMetadata().putIfAbsent(CommonConstant.METADATA_VERSION, version);
        }
        return bean;
    }
}