package com.spring.cloud.gatewayserver.config.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @program: gateway-server
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-09-23 17:08
 */
@Configuration(proxyBeanMethods = false)
public class GatewayRouteConfig {
  @Bean
  public RouteLocator myRoutes(RouteLocatorBuilder builder) {
    String httpUri = "http://httpbin.org:80";
    return builder
        .routes()
        // p.xx xxPredicateFactory
        .route(
            "path-route",
            p -> p.path("/get").filters(f -> f.addRequestHeader("Hello", "World")).uri(httpUri))
        // curl --dump-header - --header 'Host: www.hystrix.com' http://localhost:6400/delay/3
        .route(
            "host-route",
            p ->
                p.host("*.hystrix.com")
                    .filters(
                        f ->
                            f.hystrix(
                                config ->
                                    config.setName("mycmd").setFallbackUri("forward:/fallback")))
                    .uri(httpUri))
        .route(
            "rewrite_response_upper",
            r ->
                r.host("*.hystrix.com")
                    .filters(
                        f ->
                            f.prefixPath("/httpbin")
                                .modifyResponseBody(
                                    String.class,
                                    String.class,
                                    (exchange, s) -> Mono.just(s.toUpperCase())))
                    .uri(httpUri))
        .build();
  }
}
