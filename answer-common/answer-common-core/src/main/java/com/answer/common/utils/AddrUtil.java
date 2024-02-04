/**
 * @projectName answer-cloud
 * @package com.answer.common.utils
 * @className com.answer.common.utils.AddrUtil
 */
package com.answer.common.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * AddrUtil
 * @description IP地址工具类
 * @author answer_wx
 * @date 2024/1/18 23:14
 * @version 1.0
 */
@Slf4j
public class AddrUtil {
    
    private final static String UNKNOWN_STR = "unknown";

    /**
     * 获取客户端IP地址
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (isEmptyIP(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            if (isEmptyIP(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                if (isEmptyIP(ip)) {
                    ip = request.getHeader("HTTP_CLIENT_IP");
                    if (isEmptyIP(ip)) {
                        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                        if (isEmptyIP(ip)) {
                            ip = request.getRemoteAddr();
                            if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
                                // 根据网卡取本机配置的IP
                                ip = getLocalAddr();
                            }
                        }
                    }
                }
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (String strIp : ips) {
                if (!isEmptyIP(ip)) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    private static boolean isEmptyIP(String ip) {
        return StrUtil.isEmpty(ip) || UNKNOWN_STR.equalsIgnoreCase(ip);
    }

    /**
     * 获取本机的IP地址
     */
    public static String getLocalAddr() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("InetAddress.getLocalHost()-error", e);
        }
        return "";
    }
}