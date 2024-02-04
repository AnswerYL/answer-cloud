/**
 * @projectName answer-cloud
 * @package com.answer
 * @className com.answer.SearchServerCenterApp
 */
package com.answer;

import com.answer.admin.properties.IndexProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * SearchServerCenterApp
 * @description 搜索服务端
 * @author answer_wx
 * @date 2024/1/23 20:24
 * @version 1.0
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties(IndexProperties.class)
public class SearchServerCenterApp {
    public static void main(String[] args) {
        SpringApplication.run(SearchServerCenterApp.class, args);
    }
}