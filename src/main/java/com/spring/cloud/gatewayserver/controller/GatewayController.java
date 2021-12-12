package com.spring.cloud.gatewayserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @program: gateway-server
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-09-23 17:07
 */
@RestController
public class GatewayController {

  @RequestMapping("/fallback")
  public Mono<String> fallback() {
    return Mono.just("fallback");
  }

  @RequestMapping("/defaultFallback")
  public String defaultFallback() {
    return "defaultFallback";
  }
}
