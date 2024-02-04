/**
 * @projectName answer-cloud
 * @package com.answer.gateway.config
 * @className com.answer.gateway.config.AsyncTaskExecutorConfig
 */
package com.answer.gateway.config;

import com.answer.common.config.DefaultAsycTaskConfig;
import org.springframework.context.annotation.Configuration;

/**
 * AsyncTaskExecutorConfig
 * @description 线程池配置、启用异步
 * @author answer_wx
 * @date 2024/1/21 18:59
 * @version 1.0
 */
@Configuration
public class AsycTaskExecutorConfig extends DefaultAsycTaskConfig {
}