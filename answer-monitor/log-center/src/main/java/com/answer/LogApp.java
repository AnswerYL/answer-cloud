/**
 * @projectName answer-cloud
 * @package com.answer
 * @className com.answer.LogApp
 */
package com.answer;

import com.answer.search.annotation.EnableSearchClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * LogApp
 * @description 日志中心启动类
 * @author answer_wx
 * @date 2024/1/24 15:58
 * @version 1.0
 */
@EnableDiscoveryClient
@EnableSearchClient
@SpringBootApplication
public class LogApp {
    public static void main(String[] args) {
        SpringApplication.run(LogApp.class, args);
    }
}