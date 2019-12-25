package com.suood.warlord.demo.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class SelfFactoryBean implements FactoryBean<CoderSingleDog> {


  @Override
  public CoderSingleDog getObject() throws Exception {
    return new CoderSingleDog();
  }

  @Override
  public Class<?> getObjectType() {
    return CoderSingleDog.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }
}
