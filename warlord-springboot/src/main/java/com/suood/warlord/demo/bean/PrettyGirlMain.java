package com.suood.warlord.demo.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PrettyGirlMain {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context  = new AnnotationConfigApplicationContext(FactoryBeanConfig.class);
   Object o =  context.getBean("selfFactoryBean");
   Object o2 =  context.getBean("&selfFactoryBean");
    System.out.println(o.getClass().getSimpleName());
    System.out.println(o2.getClass().getSimpleName());
    context.close();
  }
}
