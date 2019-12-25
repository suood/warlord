package com.suood.warlord.demo.bean;

import org.springframework.beans.factory.FactoryBean;

public class CustomerFactory implements FactoryBean<PrettyGirl> {

  @Override
  public PrettyGirl getObject() throws Exception {
    return new PrettyGirl();
  }

  @Override
  public Class<?> getObjectType() {
    return PrettyGirl.class;
  }
}
