package com.itheima.gatewayadmin.filter;

import com.itheima.common.constants.SystemConstants;
import com.itheima.common.util.AppJwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    //获取用户携带的token令牌 并进行校验
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求对象和响应对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //白名单  直接放行
        String path = request.getURI().getPath();
        if (path.startsWith("/admin/admin/login") || path.endsWith("v2/api-docs")){
            //放行
            return chain.filter(exchange);
        }

        //从请求头中获取令牌数据
        String token = request.getHeaders().getFirst("token");

        //判断数据
        if (StringUtils.isEmpty(token)){//如果为空 则返回401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        //校验令牌是否正确
        if (AppJwtUtil.verifyToken(token) != SystemConstants.JWT_OK.intValue()){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        //放行
        return chain.filter(exchange);
    }

    //值越低 优先级越高
    @Override
    public int getOrder() {
        return -10;
    }
}
