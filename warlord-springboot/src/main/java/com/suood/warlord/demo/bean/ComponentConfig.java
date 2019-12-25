package com.suood.warlord.demo.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(basePackages={"com.suood.warlord.demo"}, excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Controller.class})} )
//    ,@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value = {XXXX.class}))
public class ComponentConfig {


  @Bean
  @Profile(value = "dev")
  // -Dspring.profiles.active=test|dev|prod 为 dev 时创建
//      ctx.getEnvironment().setActiveProfiles("test","dev");  创建
  public HelloKitty helloKitty(){
    return new HelloKitty();
  }
}
