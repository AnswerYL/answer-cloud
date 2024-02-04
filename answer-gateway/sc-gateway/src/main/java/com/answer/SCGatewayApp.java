package com.answer; /**
 * @projectName answer-cloud
 * @package PACKAGE_NAME
 * @className PACKAGE_NAME.SCGatewayApp
 */

import com.answer.common.lb.annotation.EnableBaseFeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * SCGatewayApp
 * @description
 * @author answer_wx
 * @date 2024/1/21 17:36
 * @version 1.0
 */
@EnableFeignClients
@EnableBaseFeignInterceptor
@EnableDiscoveryClient
@SpringBootApplication
public class SCGatewayApp {
    public static void main(String[] args) {
        SpringApplication.run(SCGatewayApp.class, args);
    }
}