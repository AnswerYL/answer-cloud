/**
 * @projectName answer-cloud
 * @package com.common.config
 * @className com.common.config.ExceptionAdvice
 */
package com.answer.common.config;

import com.answer.common.exception.DefaultExceptionAdvice;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * ExceptionAdvice
 * @description
 * @author answer_wx
 * @date 2024/1/23 20:39
 * @version 1.0
 */
@ControllerAdvice
public class ExceptionAdvice extends DefaultExceptionAdvice {
}