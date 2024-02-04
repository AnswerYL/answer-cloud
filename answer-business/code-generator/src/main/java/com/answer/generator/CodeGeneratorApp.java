/**
 * @projectName answer-cloud
 * @package com.answer.generator
 * @className com.answer.generator.CodeGeneratorApp
 */
package com.answer.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * CodeGeneratorApp
 * @description
 * @author answer_wx
 * @date 2024/1/23 19:09
 * @version 1.0
 */
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.answer")
@SpringBootApplication
public class CodeGeneratorApp {
    public static void main(String[] args) {
        SpringApplication.run(CodeGeneratorApp.class, args);
    }
}