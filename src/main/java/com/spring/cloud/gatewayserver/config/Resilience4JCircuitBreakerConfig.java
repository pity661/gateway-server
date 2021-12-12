package com.spring.cloud.gatewayserver.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import java.io.IOException;
import java.time.Duration;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;

/**
 * @program: gateway-server
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-09-25 14:00
 */
// @Configuration(proxyBeanMethods = false)
public class Resilience4JCircuitBreakerConfig {

  /**
   * 非响应式
   *
   * @return
   */
  //  @Bean
  public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
    return factory ->
        factory.configureDefault(
            id ->
                new Resilience4JConfigBuilder(id)
                    .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                    .timeLimiterConfig(
                        TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build())
                    .build());
  }

  /**
   * 响应式
   *
   * @return
   */
  //  @Bean
  public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultReactiveCustomizer() {
    return factory ->
        factory.configureDefault(
            id ->
                new Resilience4JConfigBuilder(id)
                    .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                    .timeLimiterConfig(
                        TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build())
                    .build());
  }

  public CircuitBreakerConfig getCircuitBreakerConfig() {
    CircuitBreakerConfig circuitBreakerConfig =
        CircuitBreakerConfig.custom()
            // 百分率形式配置失败率阈值，失败率大于阈值，CircuitBreaker变为打开状态。默认50
            .failureRateThreshold(50)
            // 慢调用率阈值。默认100
            .slowCallRateThreshold(50)
            // CircuitBreaker由打开变为半打开状态，需要等待时间 默认1min
            .waitDurationInOpenState(Duration.ofMillis(1000))
            // 配置为true会自动由打开状态变为半打开状态。不需要额外的调用触发转换
            .automaticTransitionFromOpenToHalfOpenEnabled(false)
            // 执行时长阈值，超过认为是慢调用 默认1min
            .slowCallDurationThreshold(Duration.ofSeconds(2))
            // 半开状态，配置允许调用次数 默认10
            .permittedNumberOfCallsInHalfOpenState(3)
            // 计算错误率前，至少请求要达到的次数 默认10
            .minimumNumberOfCalls(10)
            // 滑动窗口类型 基于时间和基于次数 COUNT_BASED和TIME_BASED
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
            // 配置欢动窗口大小 默认100 与类型相关秒/次
            .slidingWindowSize(5)
            // 调用异常信息
            //                .recordException(e -> e.)
            // 异常当作失败
            .recordExceptions(IOException.class)
            // 不会当作成功也不当作失败
            .ignoreExceptions()
            .build();
    return circuitBreakerConfig;
  }
}
