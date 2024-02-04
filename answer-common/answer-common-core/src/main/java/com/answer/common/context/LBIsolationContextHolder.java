/**
 * @projectName answer-cloud
 * @package com.answer.common.context
 * @className com.answer.common.context.LbIsolationContextHolder
 */
package com.answer.common.context;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * LbIsolationContextHolder
 * @description 负载均衡策略Handler
 * @author answer_wx
 * @date 2024/1/18 21:59
 * @version 1.0
 */
public class LBIsolationContextHolder {
    private static final ThreadLocal<String> VERSION_CONTEXT = new TransmittableThreadLocal<>();

    public static void setVersion(String version) {
        VERSION_CONTEXT.set(version);
    }

    public static String getVersion() {
        return VERSION_CONTEXT.get();
    }

    public static void clear() {
        VERSION_CONTEXT.remove();
    }

}