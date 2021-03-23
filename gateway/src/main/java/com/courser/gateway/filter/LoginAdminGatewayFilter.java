package com.courser.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 田付成
 * @date 2021/3/23 20:05
 */
public class LoginAdminGatewayFilter implements GatewayFilter, Ordered {
    private static final Logger LOG = LoggerFactory.getLogger(LoginAdminGatewayFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取header的token参数
        String token = exchange.getRequest().getHeaders().getFirst("token");
//        LOG.info("登录拦截开始，token：{}", token);
//        return chain.filter(exchange);







        // 请求地址中不包含/admin/的，不是控台请求，不需要拦截
        String path = exchange.getRequest().getURI().getPath();
        if (!path.contains("/admin/")) {
            return chain.filter(exchange);
        }
        if (path.contains("/system/admin/user/login")
                || path.contains("/system/admin/user/logout")
                || path.contains("/system/admin/kaptcha")) {
            LOG.info("不需要控台登录验证：{}", path);
            return chain.filter(exchange);
        }

        LOG.info("控台登录验证开始，token：{}", token);
        if (token == null || token.isEmpty()) {
            LOG.info( "token为空，请求被拦截" );
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        Object object = token;
        if (object == null) {
            LOG.warn( "token无效，请求被拦截" );
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        } else {
            LOG.info("已登录：{}", object);
            return chain.filter(exchange);
        }

    }

    @Override
    public int getOrder()
    {
        return 1;
    }
}
