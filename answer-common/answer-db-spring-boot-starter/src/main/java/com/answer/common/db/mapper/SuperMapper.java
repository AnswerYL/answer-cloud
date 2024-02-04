/**
 * @projectName answer-cloud
 * @package com.answer.db.mapper
 * @className com.answer.db.mapper.SuperMapper
 */
package com.answer.common.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * SuperMapper
 * @description mapper 父类，扫包时不要扫到
 * @author answer_wx
 * @date 2024/1/19 11:04
 * @version 1.0
 */
public interface SuperMapper<T> extends BaseMapper<T> {
    // 这里可以放一些公共的方法
}