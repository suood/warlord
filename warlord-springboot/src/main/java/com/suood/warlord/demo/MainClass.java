package com.suood.warlord.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
    //At least one annotated class must be specified
//    context.register();
    System.out.println("test lazy property");
    Object o = context.getBean("lovePerson");
    System.out.println(o);
    context.close();
  }
}
