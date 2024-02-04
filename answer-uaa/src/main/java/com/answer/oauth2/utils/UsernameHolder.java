/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.utils
 * @className com.answer.oauth2.utils.UsernameHolder
 */
package com.answer.oauth2.utils;

/**
 * UsernameHolder
 * @description
 * @author answer_wx
 * @date 2024/1/24 16:21
 * @version 1.0
 */
public class UsernameHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal();

    public static String getContext() {
        return contextHolder.get();
    }

    public static void setContext(String username) {
        contextHolder.set(username);
    }

    public static void clearContext() {
        contextHolder.remove();
    }
}