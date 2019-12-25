package com.suood.warlord.demo.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = {"com.suood.warlord.demo"}, excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})})
//    ,@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value = {XXXX.class}))
public class ComponentConfig {


  @Bean
  @Profile(value = "dev")
  // -Dspring.profiles.active=test|dev|prod 为 dev 时创建
//      ctx.getEnvironment().setActiveProfiles("test","dev");  创建
// 通过@Profile注解 来根据环境来激活标识不同的Bean
//  @Profile标识在类上，那么只有当前环境匹配，整个配置类才会生效
//  @Profile标识在Bean上 ，那么只有当前环境的Bean才会被激活
  public HelloKitty helloKitty() {
    return new HelloKitty();
  }
}
