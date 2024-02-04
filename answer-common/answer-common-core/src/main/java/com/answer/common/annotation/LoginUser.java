/**
 * @projectName answer-cloud
 * @package com.answer.common.annotation
 * @className com.answer.common.annotation.LoginUser
 */
package com.answer.common.annotation;

import java.lang.annotation.*;

/**
 * LoginUser
 * @description 请求的方法参数SysUser上添加该注解，则注入当前登录人信息
 * @author answer_wx
 * @date 2024/1/18 18:20
 * @version 1.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginUser {
    /**
     * 是否查询SysUser对象所有信息，true则通过rpc接口查询
     */
    boolean isFull() default false;
}