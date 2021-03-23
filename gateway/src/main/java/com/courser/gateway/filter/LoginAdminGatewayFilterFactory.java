package com.courser.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 田付成
 * @date 2021/3/23 20:06
 */

@Component
public class LoginAdminGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
    @Resource
    LoginAdminGatewayFilter loginAdminGatewayFilter;

    @Override
    public GatewayFilter apply(Object config) {
        return loginAdminGatewayFilter;
    }
}
