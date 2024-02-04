/**
 * @projectName answer-cloud
 * @package com.answer.common.zk.lock
 * @className com.answer.common.zk.lock.ZookeeperDistributedLock
 */
package com.answer.common.zk.lock;

import com.answer.common.constant.CommonConstant;
import com.answer.common.exception.LockException;
import com.answer.common.lock.ALock;
import com.answer.common.lock.DistributedLock;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * ZookeeperDistributedLock
 * @description ZK分布式锁实现
 * @author answer_wx
 * @date 2024/1/21 17:15
 * @version 1.0
 */
@Component
@ConditionalOnProperty(prefix = "answer.lock", name = "lockerType", havingValue = "ZK")
public class ZookeeperDistributedLock implements DistributedLock {
    @Resource
    private CuratorFramework client;

    private ALock getLock(String key) {
        InterProcessMutex lock = new InterProcessMutex(client, getPath(key));
        return new ALock(lock, this);
    }

    @Override
    public ALock lock(String key, long leaseTime, TimeUnit unit, boolean isFair) throws Exception {
        ALock zLock = this.getLock(key);
        InterProcessMutex ipm = (InterProcessMutex) zLock.getLock();
        ipm.acquire();
        return zLock;
    }

    @Override
    public ALock tryLock(String key, long waitTime, long leaseTime, TimeUnit unit, boolean isFair) throws Exception {
        ALock zLock = this.getLock(key);
        InterProcessMutex ipm = (InterProcessMutex) zLock.getLock();
        if (ipm.acquire(waitTime, unit)) {
            return zLock;
        }
        return null;
    }

    @Override
    public void unlock(Object lock) throws Exception {
        if (lock != null) {
            if (lock instanceof InterProcessMutex) {
                InterProcessMutex ipm = (InterProcessMutex) lock;
                if (ipm.isAcquiredInThisProcess()) {
                    ipm.release();
                }
            } else {
                throw new LockException("requires InterProcessMutex type");
            }
        }
    }

    private String getPath(String key) {
        return CommonConstant.PATH_SPLIT + CommonConstant.LOCK_KEY_PREFIX + CommonConstant.PATH_SPLIT + key;
    }
}