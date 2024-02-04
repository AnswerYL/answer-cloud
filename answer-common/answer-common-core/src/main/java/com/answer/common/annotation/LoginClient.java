/**
 * @projectName answer-cloud
 * @package com.answer.common.annotation
 * @className com.answer.common.annotation.LoginClient
 */
package com.answer.common.annotation;

import java.lang.annotation.*;

/**
 * LoginClient
 * @description 请求的方法参数上添加该注解，则注入当前登录账号的应用id
 * @author answer_wx
 * @date 2024/1/18 18:18
 * @version 1.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginClient {
}