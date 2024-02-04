/**
 * @projectName answer-cloud
 * @package com.answer.common.enums
 * @className com.answer.common.enums.CodeEnum
 */
package com.answer.common.enums;

/**
 * CodeEnum
 * @description
 * @author answer_wx
 * @date 2024/1/18 22:38
 * @version 1.0
 */
public enum CodeEnum {
    SUCCESS(0),
    ERROR(-1);

    private Integer code;

    CodeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}