/**
 * @projectName answer-cloud
 * @package com.answer.search.model
 * @className com.answer.search.model.AggItemVo
 */
package com.answer.search.model;

import lombok.Getter;
import lombok.Setter;

/**
 * AggItemVo
 * @description 聚合item
 * @author answer_wx
 * @date 2024/1/23 20:48
 * @version 1.0
 */
@Getter
@Setter
public class AggItemVo {
    private String name;
    private Long value;
}