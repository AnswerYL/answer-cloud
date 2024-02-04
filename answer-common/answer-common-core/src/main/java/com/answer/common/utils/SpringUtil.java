/**
 * @projectName answer-cloud
 * @package com.answer.common.utils
 * @className com.answer.common.utils.SpringUtil
 */
package com.answer.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * SpringUtil
 * @description spring获取bean工具类
 * @author answer_wx
 * @date 2024/1/18 23:31
 * @version 1.0
 */
@Component
@Order(0)
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    public static <T> T getBean(Class<T> cla) {
        return applicationContext.getBean(cla);
    }

    public static <T> T getBean(String name, Class<T> cal) {
        return applicationContext.getBean(name, cal);
    }

    public static String getProperty(String key) {
        return applicationContext.getBean(Environment.class).getProperty(key);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtil.applicationContext = applicationContext;
    }
}