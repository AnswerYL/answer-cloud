/**
 * @projectName answer-cloud
 * @package com.answer.redis.lock
 * @className com.answer.redis.lock.RedissonDistributedLock
 */
package com.answer.common.redis.lock;

import com.answer.common.constant.CommonConstant;
import com.answer.common.exception.LockException;
import com.answer.common.lock.ALock;
import com.answer.common.lock.DistributedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.util.concurrent.TimeUnit;

/**
 * RedissonDistributedLock
 * @description redisson分布式锁实现，基本锁功能的抽象实现
 * 本接口能满足绝大部分的需求，高级的锁功能，请自行扩展或直接使用原生api
 * @author answer_wx
 * @date 2024/1/19 17:26
 * @version 1.0
 */
@ConditionalOnClass(RedissonClient.class)
@ConditionalOnProperty(prefix = "answer.lock",
        name = "lockerType", havingValue = "REDIS",
        matchIfMissing = true)
public class RedissonDistributedLock implements DistributedLock {

    @Autowired
    private RedissonClient redisson;

    private ALock getLock(String key, boolean isFair) {
        RLock lock;
        if (isFair) {
            lock = redisson.getFairLock(CommonConstant.LOCK_KEY_PREFIX + ":" + key);
        } else {
            lock = redisson.getLock(CommonConstant.LOCK_KEY_PREFIX + ":" + key);
        }
        return new ALock(lock, this);
    }

    @Override
    public ALock lock(String key, long leaseTime, TimeUnit unit, boolean isFair) {
        ALock aLock = getLock(key, isFair);
        RLock lock = (RLock) aLock.getLock();
        lock.lock(leaseTime, unit);
        return aLock;
    }

    @Override
    public ALock tryLock(String key, long waitTime, long leaseTime, TimeUnit unit, boolean isFair) throws InterruptedException {
        ALock aLock = getLock(key, isFair);
        RLock lock = (RLock) aLock.getLock();
        if (lock.tryLock(waitTime, leaseTime, unit)) {
            return aLock;
        }
        return null;
    }

    @Override
    public void unlock(Object lock) {
        if (lock != null) {
            if (lock instanceof RLock) {
                RLock rLock = (RLock) lock;
                if (rLock.isLocked()) {
                    rLock.unlock();
                }
            } else {
                throw new LockException("requires RLock type");
            }
        }
    }
}