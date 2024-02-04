/**
 * @projectName answer-cloud
 * @package com.answer.common.utils
 * @className com.answer.common.utils.WebfluxResponseUtil
 */
package com.answer.common.utils;

import com.answer.common.model.Result;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * WebfluxResponseUtil
 * @description webflux 返回值工具类
 * @author answer_wx
 * @date 2024/1/18 23:33
 * @version 1.0
 */
public class WebfluxResponseUtil {

    /**
     * webflux的response返回json对象
     */
    public static Mono<Void> responseWriter(ServerWebExchange exchange, int httpStatus, String msg) {
        Result result = Result.of(null, httpStatus, msg);
        return responseWrite(exchange, httpStatus, result);
    }

    public static Mono<Void> responseFailed(ServerWebExchange exchange, String msg) {
        Result result = Result.failed(msg);
        return responseWrite(exchange, HttpStatus.INTERNAL_SERVER_ERROR.value(), result);
    }

    public static Mono<Void> responseFailed(ServerWebExchange exchange, int httpStatus, String msg) {
        Result result = Result.failed(msg);
        return responseWrite(exchange, httpStatus, result);
    }

    public static Mono<Void> responseWrite(ServerWebExchange exchange, int httpStatus, Result result) {
        if (httpStatus == 0) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setAccessControlAllowCredentials(true);
        response.getHeaders().setAccessControlAllowOrigin("*");
        response.setStatusCode(HttpStatus.valueOf(httpStatus));
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        DataBufferFactory dataBufferFactory = response.bufferFactory();
        DataBuffer buffer = dataBufferFactory.wrap(JsonUtil.toJSONString(result).getBytes(Charset.defaultCharset()));
        return response.writeWith(Mono.just(buffer)).doOnError((error) -> {
            DataBufferUtils.release(buffer);
        });
    }
}