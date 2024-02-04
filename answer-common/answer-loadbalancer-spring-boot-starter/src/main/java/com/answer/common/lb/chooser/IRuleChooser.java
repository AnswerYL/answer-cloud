/**
 * @projectName answer-cloud
 * @package com.answer.common.lb.chooser
 * @className com.answer.common.lb.chooser.IRuleChooser
 */
package com.answer.common.lb.chooser;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * IRuleChooser
 * @description service选择器接口
 * @author answer_wx
 * @date 2024/1/19 19:44
 * @version 1.0
 */
public interface IRuleChooser {
    ServiceInstance choose(List<ServiceInstance> instances);
}