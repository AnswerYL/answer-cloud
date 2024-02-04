/**
 * @projectName answer-cloud
 * @package com.answer.log.config
 * @className com.answer.log.config.TtlMDCAdapterInitializer
 */
package com.answer.common.log.config;

import org.slf4j.TtlMDCAdapter;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * TtlMDCAdapterInitializer
 * @description 初始化TtlMDCAdapter实例，并替换MDC中的adapter对象
 * @author answer_wx
 * @date 2024/1/19 11:32
 * @version 1.0
 */
public class TtlMDCAdapterInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        TtlMDCAdapter.getInstance();
    }
}