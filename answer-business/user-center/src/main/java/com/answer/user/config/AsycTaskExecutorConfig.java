/**
 * @projectName answer-cloud
 * @package com.answer.user.config
 * @className com.answer.user.config.AsycTaskExecutorConfig
 */
package com.answer.user.config;

import com.answer.common.config.DefaultAsycTaskConfig;
import org.springframework.context.annotation.Configuration;

/**
 * AsycTaskExecutorConfig
 * @description 启用线程池，异步
 * @author answer_wx
 * @date 2024/1/23 21:14
 * @version 1.0
 */
@Configuration
public class AsycTaskExecutorConfig extends DefaultAsycTaskConfig {
}