/**
 * @projectName answer-cloud
 * @package com.answer.common.config
 * @className com.answer.common.config.BannerInitializer
 */
package com.answer.common.config;

import com.answer.common.constant.CommonConstant;
import com.answer.common.utils.CustomBanner;
import com.nepxion.banner.BannerConstant;
import com.nepxion.banner.Description;
import com.nepxion.banner.LogoBanner;
import com.taobao.text.Color;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * BannerInitializer
 * @description Banner初始化
 * @author answer_wx
 * @date 2024/1/18 18:32
 * @version 1.0
 */
public class BannerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        if (!(applicationContext instanceof AnnotationConfigApplicationContext)) {
            LogoBanner logoBanner = new LogoBanner(BannerInitializer.class, "/tmp/banner.txt", "Welcome to answer", 5, 6, new Color[5], true);
            CustomBanner.show(logoBanner, new Description(BannerConstant.VERSION + ":", CommonConstant.PROJECT_VERSION, 0, 1)
                    , new Description("Github:", "https://github.com/****", 0, 1)
                    , new Description("Blog:", "https://***.gitee.io", 0, 1)
            );
        }
    }
}