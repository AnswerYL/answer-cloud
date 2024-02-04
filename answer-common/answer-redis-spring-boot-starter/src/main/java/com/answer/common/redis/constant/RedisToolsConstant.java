/**
 * @projectName answer-cloud
 * @package com.answer.redis.constant
 * @className com.answer.redis.constant.RedisToolsConstant
 */
package com.answer.common.redis.constant;

/**
 * RedisToolsConstant
 * @description Redis工具常量
 * @author answer_wx
 * @date 2024/1/19 17:21
 * @version 1.0
 */
public class RedisToolsConstant {
    private RedisToolsConstant() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * single Redis
     */
    public final static int SINGLE = 1;

    /**
     * Redis cluster
     */
    public final static int CLUSTER = 2;
}