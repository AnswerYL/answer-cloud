/**
 * @projectName answer-cloud
 * @package com.answer.common.context
 * @className com.answer.common.context.LoginUserContextHolder
 */
package com.answer.common.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.answer.common.model.SysUser;

/**
 * LoginUserContextHolder
 * @description 登录用户Holder
 * @author answer_wx
 * @date 2024/1/18 22:02
 * @version 1.0
 */
public class LoginUserContextHolder {
    private static final ThreadLocal<SysUser> CONTEXT = new TransmittableThreadLocal<>();

    public static void setUser(SysUser user) {
        CONTEXT.set(user);
    }

    public static SysUser getUser() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}