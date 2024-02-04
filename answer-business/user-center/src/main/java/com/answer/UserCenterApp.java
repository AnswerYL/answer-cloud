/**
 * @projectName answer-cloud
 * @package com.answer
 * @className com.answer.UserCenterApp
 */
package com.answer;

import com.answer.common.lb.annotation.EnableFeignInterceptor;
import com.answer.search.annotation.EnableSearchClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * UserCenterApp
 * @description 用户中心启动类
 * @author answer_wx
 * @date 2024/1/23 20:19
 * @version 1.0
 */
@EnableDiscoveryClient
@EnableSearchClient
@EnableTransactionManagement
@EnableFeignInterceptor
@SpringBootApplication
public class UserCenterApp {
    public static void main(String[] args) {
        SpringApplication.run(UserCenterApp.class, args);
    }
}