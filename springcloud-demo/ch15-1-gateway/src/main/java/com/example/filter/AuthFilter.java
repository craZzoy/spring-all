package com.example.filter;

import com.example.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;

/**
 * @Description: 检验token是否有效
 * @Author : 郑玮泽
 * @Date : 15:02 2021/2/5
 */
public class AuthFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //gatewayRoute
        Route gatewayUrl = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        final URI uri = gatewayUrl.getUri();
        final ServerHttpRequest request = exchange.getRequest();
        final HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(JwtUtil.HEADER_AUTH);
        Map<String, String> userMap = JwtUtil.validateToken(token);
        final ServerHttpRequest.Builder mutate = request.mutate();
        //是管理员用户
        if ("admin".equals(userMap.get("user")) || "spring".equals(userMap.get("user")) || "cloud".equals(userMap.get("user"))){
            mutate.header("x-user-id", userMap.get("id"));
            mutate.header("x-user-name", userMap.get("user"));
            mutate.header("x-user-serviceName", uri.getHost());
        } else {
            throw new PermissionException("user not exist, please check");
        }
        final ServerHttpRequest build = mutate.build();
        return chain.filter(exchange.mutate().request(build).build());
    }
}
