package com.suood.warlord.demo.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProcessGirls implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    if (beanName.equals("kissPrettyGirl")){
      System.out.println("before " + beanName + " init ");
    }

    return bean;
  }
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if (beanName.equals("kissPrettyGirl")){
    System.out.println("after " + beanName + " init ");
    }
    return bean;
  }
}
