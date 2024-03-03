package com.atguigu.webflux.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/3 21:09
 */
@Component
public class MyFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        System.out.println("放行前");
        Mono<Void> filter = chain.filter(exchange)
                .doFinally((signalType)->{
                    System.out.println("放行之后" + signalType);
                });        //放行
        System.out.println("放行之后");          // 不会执行，上面的执行不花时间
        return filter;
    }
}
