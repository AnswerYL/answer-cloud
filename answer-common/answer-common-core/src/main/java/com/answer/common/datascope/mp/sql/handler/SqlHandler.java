/**
 * @projectName answer-cloud
 * @package com.answer.common.datascope.mp.sql.handler
 * @className com.answer.common.datascope.mp.sql.handler.SqlHandler
 */
package com.answer.common.datascope.mp.sql.handler;

/**
 * SqlHandler
 * @description 数据权限的sql获取接口
 * @author answer_wx
 * @date 2024/1/18 22:12
 * @version 1.0
 */
public interface SqlHandler {

    /**
     * 通过这个字符替换成别名，自动的
     */
    String ALIAS_SYNBOL = "alias_";

    /**
     * 空字符串
     */
    String DO_NOTHING = "";

    /**
     * 返回需要增加的where条件，返回空字符的话则代表不需要权限控制
     * @return where条件
     */
    String handleScopeSql();
}