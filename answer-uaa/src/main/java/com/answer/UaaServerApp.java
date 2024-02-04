/**
 * @projectName answer-cloud
 * @package com.answer.oauth2
 * @className com.answer.oauth2.UaaServerApp
 */
package com.answer;

import com.answer.common.lb.annotation.EnableFeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * UaaServerApp
 * @description 认证中心启动类
 * @author answer_wx
 * @date 2024/1/24 16:14
 * @version 1.0
 */
@EnableFeignClients
@EnableFeignInterceptor
@EnableDiscoveryClient
//@EnableRedisHttpSession
@SpringBootApplication
public class UaaServerApp {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(UaaServerApp.class);
        application.setEnvironmentPrefix("answer-uaa");
        application.run(args);
    }
}