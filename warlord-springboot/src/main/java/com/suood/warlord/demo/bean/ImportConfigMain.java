package com.suood.warlord.demo.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ImportConfigMain {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ImportConfig.class);
    // import方式，只能通过全路径获取 
    Object o = context.getBean("com.suood.warlord.demo.bean.Person");
    System.out.println(o);
    
    Object hellKitty = context.getBean("helloKitty");
    context.close();
  }
}
