package com.suood.warlord.demo.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class PrettyGirl implements InitializingBean , DisposableBean {

  @Override
  public void destroy() throws Exception {
    
  }

  @Override
  public void afterPropertiesSet() throws Exception {

  }
}
