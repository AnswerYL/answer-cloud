/**
 * @projectName answer-cloud
 * @package com.answer.search.model
 * @className com.answer.search.model.LogicDelDto
 */
package com.answer.search.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * LogicDelDto
 * @description
 * @author answer_wx
 * @date 2024/1/23 21:09
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
public class LogicDelDto {
    /**
     * 逻辑删除字段名
     */
    private String logicDelField;
    /**
     * 逻辑删除字段未删除的值
     */
    private String logicNotDelValue;
}