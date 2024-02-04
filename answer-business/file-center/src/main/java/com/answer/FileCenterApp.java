package com.answer; /**
 * @projectName answer-cloud
 * @package PACKAGE_NAME
 * @className PACKAGE_NAME.FileCenterApp
 */

import com.answer.common.lb.annotation.EnableFeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * FileCenterApp
 * @description 文件中心启动类
 * @author answer_wx
 * @date 2024/1/23 19:56
 * @version 1.0
 */
@EnableDiscoveryClient
@EnableFeignClients
@EnableFeignInterceptor
@SpringBootApplication
public class FileCenterApp {
    public static void main(String[] args) {
        SpringApplication.run(FileCenterApp.class, args);
    }
}