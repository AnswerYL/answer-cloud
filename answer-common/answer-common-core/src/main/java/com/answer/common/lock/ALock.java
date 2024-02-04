/**
 * @projectName answer-cloud
 * @package com.answer.common.lock
 * @className com.answer.common.lock.ALock
 */
package com.answer.common.lock;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ALock
 * @description 锁对象抽象
 * @author answer_wx
 * @date 2024/1/18 22:46
 * @version 1.0
 */
@AllArgsConstructor
public class ALock implements AutoCloseable{

    @Getter
    private final Object lock;

    private final DistributedLock locker;

    @Override
    public void close() throws Exception {
        locker.unlock(lock);
    }
}