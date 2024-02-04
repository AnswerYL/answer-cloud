/**
 * @projectName answer-cloud
 * @package com.answer.common.exception
 * @className com.answer.common.exception.DefaultExceptionAdvice
 */
package com.answer.common.exception;

import com.answer.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

/**
 * DefaultExceptionAdvice
 * @description 通用异常处理
 * @author answer_wx
 * @date 2024/1/18 22:35
 * @version 1.0
 */
@ResponseBody
@Slf4j
public class DefaultExceptionAdvice {
    /**
     * IllegalArgumentException异常处理返回json
     * 返回状态码:400
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public <T> Result<T> badRequestException(IllegalArgumentException e) {
        return defHandler("参数解析失败", e);
    }

    /**
     * AccessDeniedException异常处理返回json
     * 返回状态码:403
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class})
    public <T> Result<T> badMethodExpressException(AccessDeniedException e) {
        return defHandler("没有权限请求当前方法", e);
    }

    /**
     * 返回状态码:405
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public <T> Result<T> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return defHandler("不支持当前请求方法", e);
    }

    /**
     * 返回状态码:415
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public <T> Result<T> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return defHandler("不支持当前媒体类型", e);
    }

    /**
     * SQLException sql异常处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({SQLException.class})
    public <T> Result<T> handleSQLException(SQLException e) {
        return defHandler("服务运行SQLException异常", e);
    }

    /**
     * BusinessException 业务异常处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException.class)
    public <T> Result<T> handleException(BusinessException e) {
        return defHandler("业务异常", e);
    }

    /**
     * IdempotencyException 幂等性异常
     * 返回状态码:200
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(IdempotencyException.class)
    public <T> Result<T> handleException(IdempotencyException e) {
        return Result.failed(e.getMessage());
    }

    /**
     * 其他异常统一处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public <T> Result<T> handleException(Exception e) {
        return defHandler("未知异常", e);
    }

    private <T> Result<T> defHandler(String msg, Exception e) {
        log.error(msg, e);
        return Result.failed(msg);
    }
}