package com.spring.cloud.gatewayserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: gateway-server
 * @description: 配置详情 application-filter-hystrix.yml - hystrix_route
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-09-27 09:58
 */
@RestController
public class HystrixRouteController {

  @RequestMapping("/consumingserviceendpoint")
  public String consumingserviceendpoint() {
    return "consumingserviceendpoint";
  }

  @RequestMapping("/backingserviceendpoint")
  public String backingserviceendpoint() {
    return "backingserviceendpoint";
  }

  @RequestMapping("/incaseoffailureusethis")
  public String incaseoffailureusethis() {
    return "incaseoffailureusethis";
  }
}
