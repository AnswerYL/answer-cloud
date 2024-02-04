/**
 * @projectName answer-cloud
 * @package com.answer.log.config
 * @className com.answer.log.config.ExceptionAdvice
 */
package com.answer.log.config;

import com.answer.common.exception.DefaultExceptionAdvice;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * ExceptionAdvice
 * @description 统一异常处理
 * @author answer_wx
 * @date 2024/1/24 16:00
 * @version 1.0
 */
@ControllerAdvice
public class ExceptionAdvice extends DefaultExceptionAdvice {
}