/**
 * @projectName answer-cloud
 * @package com.answer.common.lb.chooser
 * @className com.answer.common.lb.chooser.RandomRuleChooser
 */
package com.answer.common.lb.chooser;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.cloud.client.ServiceInstance;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * RandomRuleChooser
 * @description 随机选择器
 * @author answer_wx
 * @date 2024/1/19 19:44
 * @version 1.0
 */
@Slf4j
public class RandomRuleChooser implements IRuleChooser {
    @Override
    public ServiceInstance choose(List<ServiceInstance> instances) {
        if (CollectionUtils.isNotEmpty(instances)) {
            int randomValue = ThreadLocalRandom.current().nextInt(instances.size());
            ServiceInstance serviceInstance = instances.get(randomValue);
            log.info("选择了ip为{}, 端口为：{}的服务", serviceInstance.getHost(), serviceInstance.getPort());
            return serviceInstance;
        }
        return null;
    }
}