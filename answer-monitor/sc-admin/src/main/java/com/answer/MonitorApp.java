/**
 * @projectName answer-cloud
 * @package com.answer.gateway
 * @className com.answer.gateway.MonitorApp
 */
package com.answer;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * MonitorApp
 * @description 监控启动类
 * @author answer_wx
 * @date 2024/1/24 16:10
 * @version 1.0
 */
@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class MonitorApp {
    public static void main(String[] args) {
        SpringApplication.run(MonitorApp.class, args);
    }
}