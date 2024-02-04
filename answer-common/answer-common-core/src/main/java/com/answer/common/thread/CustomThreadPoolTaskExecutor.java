/**
 * @projectName answer-cloud
 * @package com.answer.common.utils
 * @className com.answer.common.utils.CustomThreadPoolTaskExecutor
 */
package com.answer.common.thread;

import com.alibaba.ttl.TtlCallable;
import com.alibaba.ttl.TtlRunnable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * CustomThreadPoolTaskExecutor
 * @description 自定义线程池
 * 这是{@link ThreadPoolTaskExecutor}的一个简单替换，可搭配TransmittableThreadLocal实现父子线程之间的数据传递
 * @author answer_wx
 * @date 2024/1/18 18:59
 * @version 1.0
 */
public class CustomThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
    private static final long serialVersionUID = -5887035957049288777L;

    @Override
    public void execute(Runnable runnable) {
        Runnable ttlRunnable = TtlRunnable.get(runnable);
        super.execute(Objects.requireNonNull(ttlRunnable));
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        Callable<T> ttlCallable = TtlCallable.get(task);
        return super.submit(Objects.requireNonNull(ttlCallable));
    }

    @Override
    public Future<?> submit(Runnable task) {
        Runnable ttlRunnable = TtlRunnable.get(task);
        return super.submit(Objects.requireNonNull(ttlRunnable));
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        Runnable ttlRunnable = TtlRunnable.get(task);
        return super.submitListenable(Objects.requireNonNull(ttlRunnable));
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        Callable<T> ttlCallable = TtlCallable.get(task);
        return super.submitListenable(Objects.requireNonNull(ttlCallable));
    }

}