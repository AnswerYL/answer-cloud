/**
 * @projectName answer-cloud
 * @package com.answer.common.lb.utils
 * @className com.answer.common.lb.utils.QueryUtils
 */
package com.answer.common.lb.utils;

import org.apache.commons.lang3.StringUtils;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * QueryUtils
 * @description 解析request的query参数工具
 * @author answer_wx
 * @date 2024/1/19 19:42
 * @version 1.0
 */
public class QueryUtils {

    /**
     * 通过query字符串得到参数的map
     * @param queryString ?后的字符
     * @return
     */
    public static Map<String, String> getQueryMap(String queryString){
        if(StringUtils.isNotBlank(queryString)){
            return Arrays.stream(queryString.split("&")).map(item -> item.split("="))
                    .collect(Collectors.toMap(key -> key[0], value -> value.length > 1 && StringUtils.isNotBlank(value[1]) ? value[1] : ""));
        }
        return Collections.emptyMap();
    }

    /**
     * 通过url获取参数map
     * @param uri
     * @return
     */
    public static Map<String, String> getQueryMap(URI uri){
        if(Objects.nonNull(uri)){
            return getQueryMap(uri.getQuery());
        }
        return Collections.emptyMap();
    }
}