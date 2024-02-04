/**
 * @projectName answer-cloud
 * @package com.answer.common.model
 * @className com.answer.common.model.Result
 */
package com.answer.common.model;

import com.answer.common.enums.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Result
 * @description 封装返回结果
 * @author answer_wx
 * @date 2024/1/18 22:36
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private T datas;
    private Integer resp_code;
    private String resp_msg;

    public static <T> Result<T> succeed(String msg) {
        return of(null, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed(T model, String msg) {
        return of(model, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed(T model) {
        return of(model, CodeEnum.SUCCESS.getCode(), "");
    }

    public static <T> Result<T> of(T datas, Integer code, String msg) {
        return new Result<>(datas, code, msg);
    }

    public static <T> Result<T> failed(String msg) {
        return of(null, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failed(T model, String msg) {
        return of(model, CodeEnum.ERROR.getCode(), msg);
    }
}