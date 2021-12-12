package com.spring.cloud.gatewayserver.config.filter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @program: gateway-server
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-09-24 15:32
 */
@Component
public class RedisHostKeyResolver implements KeyResolver {
  @Override
  public Mono<String> resolve(ServerWebExchange exchange) {
    return Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
  }
}
