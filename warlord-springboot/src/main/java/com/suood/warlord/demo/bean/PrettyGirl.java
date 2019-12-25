package com.suood.warlord.demo.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PrettyGirl implements InitializingBean , DisposableBean {
  @Value("${girl.name}")
  private String name;
  @Override
  public void destroy() throws Exception {
    System.out.println(this.name + " destroy !");
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("gils's name is " + this.name);
  }
}
