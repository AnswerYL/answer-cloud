/**
 * @projectName answer-cloud
 * @package com.answer.log.trace
 * @className com.answer.log.trace.DubboTraceFilter
 */
package com.answer.common.log.trace;

import cn.hutool.core.util.StrUtil;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * DubboTraceFilter
 * @description dubbo过滤器，传递traceId
 * {@link @Activate} 是 Apache Dubbo 框架中的注解，用于标识扩展点激活条件。
 * Dubbo 是一种分布式服务框架，而扩展点激活是指在 Dubbo 中，
 * 一些扩展点的实现类需要通过 @Activate 注解来指定激活条件，以决定是否激活该实现类。
 * @author answer_wx
 * @date 2024/1/19 11:49
 * @version 1.0
 */
@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER}, order = MDCTraceUtils.FILTER_ORDER)
public class DubboTraceFilter implements Filter {
    /**
     * 服务消费者：传递traceId给下游服务
     * 服务提供者：获取traceId并赋值给MDC
     * @param invoker
     * @param invocation
     * @return
     * @throws RpcException
     */
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        boolean isProviderSide = RpcContext.getContext().isProviderSide();
        if (isProviderSide) { //服务提供者逻辑
            String traceId = invocation.getAttachment(MDCTraceUtils.KEY_TRACE_ID);
            String spanId = invocation.getAttachment(MDCTraceUtils.KEY_SPAN_ID);
            if (StrUtil.isEmpty(traceId)) {
                MDCTraceUtils.addTrace();
            } else {
                MDCTraceUtils.putTrace(traceId, spanId);
            }
        } else { //服务消费者逻辑
            String traceId = MDCTraceUtils.getTraceId();
            if (StrUtil.isNotEmpty(traceId)) {
                invocation.setAttachment(MDCTraceUtils.KEY_TRACE_ID, traceId);
                invocation.setAttachment(MDCTraceUtils.KEY_SPAN_ID, MDCTraceUtils.getNextSpanId());
            }
        }
        try {
            return invoker.invoke(invocation);
        } finally {
            if (isProviderSide) {
                MDCTraceUtils.removeTrace();
            }
        }
    }
}