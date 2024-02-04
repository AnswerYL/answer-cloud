/**
 * @projectName answer-cloud
 * @package com.answer.common.service
 * @className com.answer.common.service.ISuperService
 */
package com.answer.common.service;

import com.answer.common.lock.DistributedLock;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * ISuperService
 * @description service接口父类
 * @author answer_wx
 * @date 2024/1/18 23:05
 * @version 1.0
 */
public interface ISuperService<T> extends IService<T> {
    // 通过加锁保证新增、更新接口的幂等性

    /**
     * 幂等性新增记录
     * String username = sysUser.getUsername();
     * boolean result = super.saveIdempotency(sysUser, lock
     *  , LOCK_KEY_USERNAME+username
     *  , new QueryWrapper<SysUser>().eq("username", username));
     *
     * @param entity 实体对象
     * @param locker 锁实例
     * @param lockKey 锁的key
     * @param countWrapper 判断是否存在的条件
     * @param msg 对象已存在提示信息
     * @return
     * @throws Exception
     */
    boolean saveIdempotency(T entity, DistributedLock locker, String lockKey, Wrapper<T> countWrapper, String msg) throws Exception;

    boolean saveIdempotency(T entity, DistributedLock locker, String lockKey, Wrapper<T> countWrapper) throws Exception;

    /**
     * 幂等性新增或更新记录
     * String username = sysUser.getUsername();
     * boolean result = super.saveOrUpdateIdempotency(sysUser, lock
     *   , LOCK_KEY_USERNAME+username
     *   , new QueryWrapper<SysUser>().eq("username", username));
     *
     * @param entity 实体对象
     * @param locker 锁实例
     * @param lockKey 锁的key
     * @param countWrapper 判断是否存在的条件
     * @param msg 对象已存在提示信息
     * @return
     * @throws Exception
     */
    boolean saveOrUpdateIdempotency(T entity, DistributedLock locker, String lockKey, Wrapper<T> countWrapper, String msg) throws Exception;

    boolean saveOrUpdateIdempotency(T entity, DistributedLock locker, String lockKey, Wrapper<T> countWrapper) throws Exception;
}