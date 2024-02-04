/**
 * @projectName answer-cloud
 * @package com.answer.common.datascope.mp.sql.handler
 * @className com.answer.common.datascope.mp.sql.handler.DefaultSqlHandler
 */
package com.answer.common.datascope.mp.sql.handler;

/**
 * DefaultSqlHandler
 * @description 默认
 * @author answer_wx
 * @date 2024/1/18 22:32
 * @version 1.0
 */
public class DefaultSqlHandler implements SqlHandler {
    @Override
    public String handleScopeSql() {
        return DO_NOTHING;
    }
}