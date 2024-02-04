/**
 * @projectName answer-cloud
 * @package com.answer.common.config
 * @className com.answer.common.config.DefaultAsycTaskConfig
 */
package com.answer.common.config;

import com.answer.common.thread.CustomThreadPoolTaskExecutor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * DefaultAsycTaskConfig
 * @description 默认异步任务配置, 自定义线程池
 * @author answer_wx
 * @date 2024/1/18 18:55
 * @version 1.0
 */
@Getter
@Setter
@EnableAsync(proxyTargetClass = true)
public class DefaultAsycTaskConfig {
    /**
     *  线程池维护线程的最小数量.
     */
    @Value("${asyc-task.corePoolSize:10}")
    private int corePoolSize;
    /**
     *  线程池维护线程的最大数量
     */
    @Value("${asyc-task.maxPoolSize:200}")
    private int maxPoolSize;
    /**
     *  队列最大长度
     */
    @Value("${asyc-task.queueCapacity:10}")
    private int queueCapacity;
    /**
     *  线程池前缀
     */
    @Value("${asyc-task.threadNamePrefix:AN-Executor-}")
    private String threadNamePrefix;

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new CustomThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.setThreadNamePrefix(threadNamePrefix);

        // 拒绝策略：当pool已经达到max size时，如何处理新来的任务
        // CALLER_RUNS：不创建新线程中执行任务，而是由调用者所在线程执行

        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }
}