/**
 * @projectName answer-cloud
 * @package com.answer.user.config
 * @className com.answer.user.config.ExceptionAdvice
 */
package com.answer.user.config;

import com.answer.common.exception.DefaultExceptionAdvice;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * ExceptionAdvice
 * @description
 * @author answer_wx
 * @date 2024/1/23 21:16
 * @version 1.0
 */
@ControllerAdvice
public class ExceptionAdvice extends DefaultExceptionAdvice {
}