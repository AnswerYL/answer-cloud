/**
 * @projectName answer-cloud
 * @package com.answer.common.context
 * @className com.answer.common.context.TenantContextHolder
 */
package com.answer.common.context;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * TenantContextHolder
 * @description 租户Holder
 * @author answer_wx
 * @date 2024/1/18 22:02
 * @version 1.0
 */
public class TenantContextHolder {
    /**
     * 支持父子线程之间的数据传递
     */
    private static final ThreadLocal<String> CONTEXT = new TransmittableThreadLocal<>();

    public static void setTenant(String tenant) {
        CONTEXT.set(tenant);
    }

    public static String getTenant() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}