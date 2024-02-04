/**
 * @projectName answer-cloud
 * @package com.answer.common.service.impl
 * @className com.answer.common.service.impl.SuperServiceImpl
 */
package com.answer.common.service.impl;

import cn.hutool.core.util.StrUtil;
import com.answer.common.exception.IdempotencyException;
import com.answer.common.exception.LockException;
import com.answer.common.lock.ALock;
import com.answer.common.lock.DistributedLock;
import com.answer.common.service.ISuperService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * SuperServiceImpl
 * @description service父类实现
 * @author answer_wx
 * @date 2024/1/18 23:10
 * @version 1.0
 */
public class SuperServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements ISuperService<T> {
    @Override
    public boolean saveIdempotency(T entity, DistributedLock locker, String lockKey, Wrapper<T> countWrapper, String msg) throws Exception {
        if (locker == null) {
            throw new LockException("DistributedLock is null");
        }
        if (StrUtil.isEmpty(lockKey)) {
            throw new LockException("lockKey is null");
        }
        try (
                ALock lock = locker.tryLock(lockKey, 10, 60, TimeUnit.SECONDS);
        ) {
            if (lock != null) {
                //判断记录是否已存在
                long count = super.count(countWrapper);
                if (count == 0) {
                    return super.save(entity);
                } else {
                    if (StrUtil.isEmpty(msg)) {
                        msg = "已存在";
                    }
                    throw new IdempotencyException(msg);
                }
            } else {
                throw new LockException("锁等待超时");
            }
        }
    }

    @Override
    public boolean saveIdempotency(T entity, DistributedLock locker, String lockKey, Wrapper<T> countWrapper) throws Exception {
        return saveIdempotency(entity, locker, lockKey, countWrapper, null);
    }

    @Override
    public boolean saveOrUpdateIdempotency(T entity, DistributedLock locker, String lockKey, Wrapper<T> countWrapper, String msg) throws Exception {
        if (null != entity) {
            Class<?> cls = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            if (null != tableInfo && StrUtil.isNotEmpty(tableInfo.getKeyProperty())) {
                Object idVal = ReflectionKit.getFieldValue(entity, tableInfo.getKeyProperty());
                if (StringUtils.checkValNull(idVal) || Objects.isNull(getById((Serializable) idVal))) {
                    if (StrUtil.isEmpty(msg)) {
                        msg = "已存在";
                    }
                    return this.saveIdempotency(entity, locker, lockKey, countWrapper, msg);
                } else {
                    return updateById(entity);
                }
            } else {
                throw ExceptionUtils.mpe("Error:  Can not execute. Could not find @TableId.");
            }
        }
        return false;
    }

    @Override
    public boolean saveOrUpdateIdempotency(T entity, DistributedLock locker, String lockKey, Wrapper<T> countWrapper) throws Exception {
        return this.saveOrUpdateIdempotency(entity, locker, lockKey, countWrapper, null);
    }
}