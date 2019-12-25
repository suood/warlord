package com.suood.warlord.demo.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
    System.out.println("test lazy property");
    Object o = context.getBean("lovePerson");
    System.out.println(o);
    context.close();
  }
}
