package com.spring.cloud.gatewayserver.config.filter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * @program: gateway-server
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-09-24 16:00
 */
// @Configuration(proxyBeanMethods = false)
public class MyKeyResolverConfig {
  @Bean
  public RedisHostKeyResolver redisHostKeyResolver() {
    return new RedisHostKeyResolver();
  }

  /**
   * 只有primary的生效
   *
   * @return
   */
  @Primary
  @Bean
  public KeyResolver methodKeyResolver() {
    return exchange -> Mono.just(exchange.getRequest().getMethod().name());
  }
}
