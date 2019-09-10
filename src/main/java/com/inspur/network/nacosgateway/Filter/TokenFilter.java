package com.inspur.network.nacosgateway.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;



/**
 * @program: nacos-gateway
 * @description: 自定义的全局过滤器，没有token不允许继续执行路由表
 * @author: maqian
 * @create: 2019-09-02 09:32
 **/
@Component
public class TokenFilter implements GlobalFilter, Ordered {

    Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        //如果token为空，重定向到登录页面
        if (token == null || token.isEmpty()) {
            logger.info( "token is empty..." );
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.SEE_OTHER);
            String url="https://www.baidu.com/";
            response.getHeaders().set(HttpHeaders.LOCATION, url);
            return response.setComplete();
        }
        //继续过滤器链，选择在当前或者某个过滤器执行检查token的逻辑（包含加解密）
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
