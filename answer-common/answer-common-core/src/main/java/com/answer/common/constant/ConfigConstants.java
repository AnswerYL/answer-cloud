/**
 * @projectName answer-cloud
 * @package com.answer.common.constant
 * @className com.answer.common.constant.ConfigConstants
 */
package com.answer.common.constant;

/**
 * ConfigConstants
 * @description 配置项常量
 * @author answer_wx
 * @date 2024/1/18 18:26
 * @version 1.0
 */
public interface ConfigConstants {

    /**
     * 是否开启自定义隔离规则
     */
    String CONFIG_RIBBON_ISOLATION_ENABLED = "answer.ribbon.isolation.enabled";

    String CONFIG_LOADBALANCE_ISOLATION = "answer.loadbalance.isolation";

    String CONFIG_LOADBALANCE_ISOLATION_ENABLE = CONFIG_LOADBALANCE_ISOLATION + ".enabled";

    String CONFIG_LOADBALANCE_ISOLATION_CHOOSER = CONFIG_LOADBALANCE_ISOLATION + ".chooser";

    String CONFIG_LOADBALANCE_VERSION = "answer.loadbalance.version";
}