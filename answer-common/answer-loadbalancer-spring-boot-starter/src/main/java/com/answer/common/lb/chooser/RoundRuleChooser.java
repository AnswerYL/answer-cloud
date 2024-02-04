/**
 * @projectName answer-cloud
 * @package com.answer.common.lb.chooser
 * @className com.answer.common.lb.chooser.RoundRuleChooser
 */
package com.answer.common.lb.chooser;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.cloud.client.ServiceInstance;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * RoundRuleChooser
 * @description 轮询选择器
 * @author answer_wx
 * @date 2024/1/19 19:46
 * @version 1.0
 */
@Slf4j
public class RoundRuleChooser implements IRuleChooser {
    private AtomicInteger position;

    public RoundRuleChooser() {
        this.position = new AtomicInteger(1000);
    }

    @Override
    public ServiceInstance choose(List<ServiceInstance> instances) {
        if (CollectionUtils.isNotEmpty(instances)) {
            // position自增与instances.size()取模
            ServiceInstance serviceInstance = instances.get(Math.abs(position.incrementAndGet() % instances.size()));
            log.info("选择了ip为{}, 端口为：{}的服务", serviceInstance.getHost(), serviceInstance.getPort());
            return serviceInstance;
        }
        return null;
    }
}