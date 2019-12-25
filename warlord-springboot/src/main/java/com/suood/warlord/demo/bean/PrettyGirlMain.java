package com.suood.warlord.demo.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PrettyGirlMain {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context  = new AnnotationConfigApplicationContext(FactoryBeanConfig.class);
//    context.getBean();
    context.close();
  }
}
