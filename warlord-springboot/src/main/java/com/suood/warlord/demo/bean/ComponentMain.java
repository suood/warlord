package com.suood.warlord.demo.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComponentMain {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ComponentConfig.class);
    System.out.println("test lazy property");
    Object o = context.getBean("lovePerson");
    System.out.println(o);
    context.close();
  }
}
